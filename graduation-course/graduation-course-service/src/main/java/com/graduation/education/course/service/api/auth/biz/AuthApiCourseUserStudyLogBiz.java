package com.graduation.education.course.service.api.auth.biz;

import com.graduation.education.course.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.graduation.education.course.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.graduation.education.course.service.dao.CourseChapterPeriodDao;
import com.graduation.education.course.service.dao.CourseDao;
import com.graduation.education.course.service.dao.CourseUserStudyLogDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.Course;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseUserStudyLog;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample.Criteria;
import com.graduation.education.util.base.BaseBiz;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class AuthApiCourseUserStudyLogBiz extends BaseBiz {

	@Autowired
	private CourseUserStudyLogDao courseUserStudyLogDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseChapterPeriodDao periodDao;

	/**
	 * 最近学习日志分页列出接口
	 *
	 * @param bo
	 * @return
	 * @author wuyun
	 */
	public Result<Page<AuthCourseUserStudyLogPageDTO>> list(AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
		CourseUserStudyLogExample example = new CourseUserStudyLogExample();
		Criteria c = example.createCriteria();
		if (authCourseUserStudyLogPageBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}
		c.andUserNoEqualTo(authCourseUserStudyLogPageBO.getUserNo());
		Page<CourseUserStudyLog> page = courseUserStudyLogDao.listForPage(authCourseUserStudyLogPageBO.getPageCurrent(), authCourseUserStudyLogPageBO.getPageSize(), example);
		Page<AuthCourseUserStudyLogPageDTO> dtoList = PageUtil.transform(page, AuthCourseUserStudyLogPageDTO.class);
		for (AuthCourseUserStudyLogPageDTO dto : dtoList.getList()) {
			// 查看课程信息
			Course course = courseDao.getById(dto.getCourseId());
			// 查看课时信息
			CourseChapterPeriod period = periodDao.getById(dto.getPeriodId());

			dto.setCourseName(course.getCourseName());
			dto.setPeriodName(period.getPeriodName());
		}
		return Result.success(dtoList);
	}

}
