package com.graduation.education.user.service.api.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.graduation.education.frame.utils.RedisOperations;
import com.graduation.education.user.common.RedisKey;
import com.graduation.education.user.common.req.StudentSignREQ;
import com.graduation.education.user.common.req.TaskREQ;
import com.graduation.education.user.common.resq.CategoryRESQ;
import com.graduation.education.user.common.resq.ContentRESQ;
import com.graduation.education.user.common.resq.TaskRESQ;
import com.graduation.education.user.service.dao.impl.mapper.*;
import com.graduation.education.user.service.dao.impl.mapper.entity.*;
import com.graduation.education.util.ThreadPoolService;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.tools.BeanUtil;
import com.graduation.education.util.tools.JSONUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 通知信息
 *
 * @author YZJ
 */
@Component
public class ApiCommonBiz {

    @Autowired
    private AdviceMapper adviceMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ThreadPoolService threadPoolService;

    @Autowired
    private StudentSignMapper studentSignMapper;

    @Autowired
    private RedisOperations redisOperations;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskItemMapper taskItemMapper;

    public List<Advice> list(String classNo) {
        AdviceExample example = new AdviceExample();
        AdviceExample.Criteria criteria = example.createCriteria();
        criteria.andClassNoEqualTo(classNo);
        return adviceMapper.selectByExampleWithBLOBs(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> signStudent(StudentSignREQ studentSignREQ) {
        StudentSign studentSign = BeanUtil.copyProperties(studentSignREQ, StudentSign.class);
        Date date = new Date();
        studentSign.setCreateTime(date);
        studentSign.setModifyTime(date);

        //校验手机和手机型号是否发生变更，如果变更需要学生申请置换，老师同意后方可生效，否则属于违规操作
        String classNo = studentSignREQ.getClassNo();
        String studentNo = studentSignREQ.getStudentNo();
        String studentKey = RedisKey.buildKey("user", classNo);
        String studentJson = redisOperations.hget(studentKey, studentNo);
        Student student;
        if (studentJson == null) {
            StudentExample studentExample = new StudentExample();
            StudentExample.Criteria criteria = studentExample.createCriteria();
            criteria.andStudentNoEqualTo(studentNo);
            List<Student> studentList = studentMapper.selectByExample(studentExample);
            if (CollectionUtils.isEmpty(studentList)) {
                return ResultEnum.STUDENT_REQUISITION_SIGN.build();
            }
            student = studentList.get(0);
        } else {
            student = JSONUtil.parseObject(studentJson, Student.class);
        }
        //校验
        String phoneBrand = studentSignREQ.getPhoneBrand();
        String phoneModel = studentSignREQ.getPhoneModel();
        if (student == null
                || !Objects.equals(phoneBrand, student.getPhoneBrand())
                || !Objects.equals(phoneModel, student.getPhoneModel())) {
            //标识一下手机型号或者手机发生变化
            //数据库也来一条记录
            studentSign.setChangePhone(Byte.parseByte("1"));
            threadPoolService.asyncExec(() -> {
                //手机型号改变了的
                String key = RedisKey.buildKey("common", classNo + "_changePhone");
                redisOperations.hsetEx(key, studentNo, JSONUtil.toJSONString(studentSign), 1, TimeUnit.DAYS);
            });
        } else {
            //手机型号未改变的
            studentSign.setChangePhone(Byte.parseByte("0"));
            threadPoolService.asyncExec(() -> {
                String key = RedisKey.buildKey("common", classNo);
                redisOperations.hsetEx(key, studentNo, JSONUtil.toJSONString(studentSign), 1, TimeUnit.DAYS);
            });
        }
        int insert = studentSignMapper.insert(studentSign);
        if (insert > 0) {
            return Result.success("签到成功");
        }
        return Result.error("签到失败!!!");
    }

    public Result<TaskRESQ> getAllTask(TaskREQ taskREQ) {
        TaskRESQ taskRESQ = new TaskRESQ();
        getTaskItemById(taskREQ, taskRESQ, true);
        return Result.success(taskRESQ);
    }

    private List<TaskItem> getTaskItemById(TaskREQ taskREQ, TaskRESQ taskRESQ, boolean flag) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andClassNoEqualTo(taskREQ.getClassNo());
        criteria.andCollegeEqualTo(taskREQ.getCollege());
        List<Task> all = taskMapper.selectByExample(example);

        List<String> firstTask = Lists.newArrayList();
        Map<Long, String> firstTaskMap = Maps.newHashMap();
        List<Long> secondTaskId = Lists.newArrayList();
        Map<String, List<String>> secondTaskMap = Maps.newHashMap();

        for (Task task : all) {
            if (task.getParentId() == 0L) {
                //一级
                firstTask.add(task.getName());
                firstTaskMap.put(task.getId(), task.getName());
            }
        }
        for (Task task : all) {
            if (task.getParentId() == 0L) {
                //一级
                continue;
            }
            //二级
            Long parentId = task.getParentId();
            String firstName = firstTaskMap.get(parentId);
            //二级名称
            String name = task.getName();
            List<String> tasks = secondTaskMap.get(firstName);
            if (CollectionUtils.isEmpty(tasks)) {
                tasks = Lists.newArrayList();
            }
            //需要先判断该
            String firstTaskName = taskREQ.getFirstTask();
            if (StringUtils.isNotBlank(firstTaskName)) {
                String taskName = firstTaskMap.get(task.getParentId());
                String secondTaskName = taskREQ.getSecondTask();
                if (taskName.equals(firstTaskName)) {
                    if (StringUtils.isNotBlank(secondTaskName)) {
                        if (name.equals(secondTaskName)) {
                            secondTaskId.add(task.getId());
                        }
                    } else {
                        secondTaskId.add(task.getId());
                    }
                }
            } else {
                secondTaskId.add(task.getId());
            }
            tasks.add(name);
            secondTaskMap.put(firstName, tasks);
        }

        //获取taskItem
        TaskItemExample taskItemExample = new TaskItemExample();
        TaskItemExample.Criteria criteria1 = taskItemExample.createCriteria();
        if (!secondTaskId.isEmpty()) {
            criteria1.andTaskIdIn(secondTaskId);
        }
        Integer page = taskREQ.getPage();
        Integer size = taskREQ.getSize();
        PageHelper.startPage((page - 1) * size, size);
        List<TaskItem> taskItems = taskItemMapper.selectByExample(taskItemExample);
        if (flag) {
            taskRESQ.setFirstTask(firstTask);
            taskRESQ.setSecondTaskMap(secondTaskMap);
            taskRESQ.setTaskItemList(taskItems);
        }
        return taskItems;
    }

    public PageInfo<TaskItem> getPage(TaskREQ taskREQ) {
        List<TaskItem> taskItemList = getTaskItemById(taskREQ, null, false);
        return new PageInfo<>(taskItemList);
    }

    public Result<ContentRESQ> getTaskItemContent(Long id, Long userNo) {
        String contentKey = RedisKey.buildKey("content:click", id + "");
        String visitKey = RedisKey.buildKey("content:visit", id + "");
        //点击量 string
        Long clickNum = redisOperations.incr(contentKey);
        //访问人数 set集合 防止重复计算
        Long visitNum = redisOperations.sadd(visitKey, userNo + "");
        String content = taskItemMapper.selectById(id);
        ContentRESQ contentRESQ = ContentRESQ.builder().clickNum(clickNum).visitNum(visitNum).content(content).build();
        return Result.success(contentRESQ);
    }

    public CategoryRESQ getAllCategories() {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        List<Task> all = taskMapper.selectByExample(example);

        List<String> firstTask = Lists.newArrayList();
        Map<Long, String> firstTaskMap = Maps.newHashMap();
        Map<String, List<Task>> secondTask = Maps.newHashMap();

        for (Task task : all) {
            if (task.getParentId() == 0L) {
                //一级
                firstTask.add(task.getName());
                firstTaskMap.put(task.getId(), task.getName());
            }
        }
        for (Task task : all) {
            Long parentId;
            if ((parentId = task.getParentId()) == 0L) {
                //一级
                continue;
            }
            String name = firstTaskMap.get(parentId);
            List<Task> secondTaskList = secondTask.get(name);
            if (CollectionUtils.isEmpty(secondTaskList)) {
                secondTaskList = Lists.newArrayList();
                secondTask.put(name, secondTaskList);
            }
            secondTaskList.add(task);
        }

        CategoryRESQ categoryRESQ = CategoryRESQ.builder().firstTask(firstTask).secondTask(secondTask).build();
        return categoryRESQ;
    }
}
