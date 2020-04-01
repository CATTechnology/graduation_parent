package com.graduation.education.course.service.feign.biz;

import com.graduation.education.course.feign.qo.CourseUserStudyQO;
import com.graduation.education.course.feign.vo.CourseUserStudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graduation.education.course.service.dao.CourseUserStudyDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseUserStudy;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseUserStudyExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;

/**
 * 课程用户关联表
 *
 * @author wujing
 */
@Component
public class FeignCourseUserStudyBiz {

    @Autowired
    private CourseUserStudyDao dao;

    public Page<CourseUserStudyVO> listForPage(CourseUserStudyQO qo) {
        CourseUserStudyExample example = new CourseUserStudyExample();
        example.setOrderByClause(" id desc ");
        Page<CourseUserStudy> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseUserStudyVO.class);
    }

    public int save(CourseUserStudyQO qo) {
        CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseUserStudyVO getById(Long id) {
        CourseUserStudy record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseUserStudyVO.class);
    }

    public int updateById(CourseUserStudyQO qo) {
        CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
        return dao.updateById(record);
    }

}
