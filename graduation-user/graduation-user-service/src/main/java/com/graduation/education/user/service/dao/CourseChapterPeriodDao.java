package com.graduation.education.user.service.dao;

import com.graduation.education.user.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.graduation.education.user.service.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.graduation.education.util.base.Page;

import java.util.List;

public interface CourseChapterPeriodDao {
	int save(CourseChapterPeriod record);

	int deleteById(Long id);

	int updateById(CourseChapterPeriod record);

	CourseChapterPeriod getById(Long id);

	Page<CourseChapterPeriod> listForPage(int pageCurrent, int pageSize, CourseChapterPeriodExample example);

	List<CourseChapterPeriod> listByChapterId(Long chapterId);

	/**
	 * 根据章节编号和状态查找可用的课时信息集合
	 * 
	 * @author LHR
	 * @param chapterId
	 * @return
	 */
	List<CourseChapterPeriod> listByChapterIdAndStatusId(Long chapterId, Integer statusId);

	/**
	 * 根据课时编号查找课时信息集合
	 * 
	 * @param videoNo
	 * @return
	 * @author wuyun
	 */
	CourseChapterPeriod getByVideoNo(Long videoNo);

	/**
	 * 根据视频编号查找课时信息
	 * 
	 * @param videoNo
	 * @return
	 */
	List<CourseChapterPeriod> listByVideoNo(Long videoNo);

}