package com.graduation.education.user.service.api.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.TeacherREQ;
import com.graduation.education.user.service.dao.impl.mapper.StudentMapper;
import com.graduation.education.user.service.dao.impl.mapper.TeacherMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.user.service.dao.impl.mapper.entity.Teacher;
import com.graduation.education.user.service.dao.impl.mapper.entity.TeacherExample;
import com.graduation.education.util.base.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:22
 */
@Component
public class ApiTeacherBiz {

    @Resource
    private TeacherMapper teacherMapper;

    public PageInfo<Teacher> getAllByPage(TeacherREQ teacherREQ) {
        List<Teacher> teacherList = (List<Teacher>) execute((example) -> {
            TeacherExample.Criteria criteria = example.createCriteria();
            String name = teacherREQ.getName();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name + "%");
            }

            String realName = teacherREQ.getRealName();
            if (StringUtils.isNotBlank(realName)) {
                criteria.andRealNameLike("%" + realName + "%");
            }

            String number = teacherREQ.getNumber();
            if (StringUtils.isNotBlank(number)) {
                criteria.andNumberEqualTo(number);
            }

            example.setOrderByClause("modify_time desc");
            PageHelper.startPage(teacherREQ.getPage(), teacherREQ.getSize());
            return teacherMapper.selectByExample(example);
        });

        return new PageInfo<>(teacherList);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(Long[] ids) {
        int i = (int) execute((example) -> {
            TeacherExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(Arrays.asList(ids));
            return teacherMapper.deleteByExample(example);
        });
        return i > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    public Result<String> add(Teacher teacher) {
        Date date = new Date();
        teacher.setCreateTime(date);
        teacher.setModifyTime(date);
        return teacherMapper.insertSelective(teacher) > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    public Result<String> update(Teacher teacher) {
        int i = (int) execute((example) -> {
            TeacherExample.Criteria criteria = example.createCriteria();
            return teacherMapper.updateByPrimaryKeySelective(teacher);
        });
        return i > 0 ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 根据code查询通知
     * @param key
     * @return
     */
    public Teacher findOneByCode(Long key) {
        List<Teacher> teacherList = (List<Teacher> ) execute((example) -> {
            TeacherExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(key);
            return teacherMapper.selectByExample(example);
        });
        return CollectionUtils.isNotEmpty(teacherList) ? teacherList.get(0) : null;
    }

    /**
     * 模板方法
     *
     * @return
     */
    private Object execute(TeacherExampleCriteria teacherExampleCriteria) {
        TeacherExample teacherExample = new TeacherExample();
        return teacherExampleCriteria.execute(teacherExample);
    }


    private interface TeacherExampleCriteria {
        Object execute(TeacherExample teacherExample);
    }

}
