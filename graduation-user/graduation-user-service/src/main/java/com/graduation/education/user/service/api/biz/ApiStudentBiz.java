package com.graduation.education.user.service.api.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.StudentREQ;
import com.graduation.education.user.service.dao.impl.mapper.StudentMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.Student;
import com.graduation.education.user.service.dao.impl.mapper.entity.StudentExample;
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
public class ApiStudentBiz {

    @Resource
    private StudentMapper studentMapper;

    public PageInfo<Student> getAllByPage(StudentREQ studentREQ) {
        List<Student> studentList = (List<Student>) execute((example) -> {
            StudentExample.Criteria criteria = example.createCriteria();
            String name = studentREQ.getName();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name + "%");
            }

            String realName = studentREQ.getRealName();
            if (StringUtils.isNotBlank(realName)) {
                criteria.andRealNameLike("%" + realName + "%");
            }

            String classNo = studentREQ.getClassNo();
            if (StringUtils.isNotBlank(classNo)) {
                criteria.andClassNoEqualTo(classNo);
            }

            String studentNo = studentREQ.getStudentNo();
            if (StringUtils.isNotBlank(studentNo)) {
                criteria.andStudentNoEqualTo(studentNo);
            }

            String mobile = studentREQ.getMobile();
            if (StringUtils.isNotBlank(mobile)) {
                criteria.andMobileEqualTo(mobile);
            }

            String professional = studentREQ.getProfessional();
            if (StringUtils.isNotBlank(professional)) {
                criteria.andProfessionalLike("%"+professional+"%");
            }

            String college = studentREQ.getCollege();
            if (StringUtils.isNotBlank(college)) {
                criteria.andCollegeLike("%"+college+"%");
            }

            example.setOrderByClause("modify_time desc");
            PageHelper.startPage(studentREQ.getPage(), studentREQ.getSize());
            return studentMapper.selectByExample(example);
        });

        return new PageInfo<>(studentList);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(Long[] ids) {
        int i = (int) execute((example) -> {
            StudentExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(Arrays.asList(ids));
            return studentMapper.deleteByExample(example);
        });
        return i > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    public Result<String> add(Student student) {
        Date date = new Date();
        student.setCreateTime(date);
        student.setModifyTime(date);
        return studentMapper.insertSelective(student) > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    public Result<String> update(Student student) {
        int i = (int) execute((example) -> {
            StudentExample.Criteria criteria = example.createCriteria();
            return studentMapper.updateByPrimaryKeySelective(student);
        });
        return i > 0 ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 根据code查询通知
     * @param key
     * @return
     */
    public Student findOneByCode(Long key) {
        List<Student> studentList = (List<Student> ) execute((example) -> {
            StudentExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(key);
            return studentMapper.selectByExample(example);
        });
        return CollectionUtils.isNotEmpty(studentList) ? studentList.get(0) : null;
    }

    /**
     * 模板方法
     *
     * @return
     */
    private Object execute(StudentExampleCriteria studentExampleCriteria) {
        StudentExample studentExample = new StudentExample();
        return studentExampleCriteria.execute(studentExample);
    }


    private interface StudentExampleCriteria {
        Object execute(StudentExample studentExample);
    }

}
