package com.graduation.education.course.service.feign.biz;

import com.graduation.education.course.feign.qo.CourseIntroduceQO;
import com.graduation.education.course.feign.vo.CourseIntroduceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graduation.education.course.service.dao.CourseIntroduceDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseIntroduceExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@Component
public class FeignCourseIntroduceBiz {

    @Autowired
    private CourseIntroduceDao dao;

    public Page<CourseIntroduceVO> listForPage(CourseIntroduceQO qo) {
        CourseIntroduceExample example = new CourseIntroduceExample();
        example.setOrderByClause(" id desc ");
        Page<CourseIntroduce> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseIntroduceVO.class);
    }

    public int save(CourseIntroduceQO qo) {
        CourseIntroduce record = BeanUtil.copyProperties(qo, CourseIntroduce.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseIntroduceVO getById(Long id) {
        CourseIntroduce record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseIntroduceVO.class);
    }

    public int updateById(CourseIntroduceQO qo) {
        CourseIntroduce record = BeanUtil.copyProperties(qo, CourseIntroduce.class);
        return dao.updateById(record);
    }

}
