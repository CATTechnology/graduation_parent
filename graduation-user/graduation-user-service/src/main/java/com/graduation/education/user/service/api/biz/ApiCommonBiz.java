package com.graduation.education.user.service.api.biz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.graduation.education.frame.utils.RedisOperations;
import com.graduation.education.user.common.RedisKey;
import com.graduation.education.user.common.req.StudentSignREQ;
import com.graduation.education.user.common.req.TaskREQ;
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
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andClassNoEqualTo(taskREQ.getClassNo());
        criteria.andCollegeEqualTo(taskREQ.getCollege());
        List<Task> all = taskMapper.selectByExample(example);
        List<String> firstTask = Lists.newArrayList();
        Map<Long, String> firstTaskMap = Maps.newHashMap();
        List<Long> secondTaskId = Lists.newArrayList();
        Map<String, List<String>> map = Maps.newHashMap();
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
            List<String> tasks = map.get(firstName);
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
            map.put(firstName, tasks);
        }

        //获取taskItem
        TaskItemExample taskItemExample = new TaskItemExample();
        TaskItemExample.Criteria criteria1 = taskItemExample.createCriteria();
        if (!secondTaskId.isEmpty()) {
            criteria1.andTaskIdIn(secondTaskId);
        }
        List<TaskItem> taskItems = taskItemMapper.selectByExample(taskItemExample);
        TaskRESQ taskRESQ = TaskRESQ.builder().firstTask(firstTask).secondTaskMap(map).taskItemList(taskItems).build();
        return Result.success(taskRESQ);
    }
}
