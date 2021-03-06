package com.graduation.education.course.service.dao;

import java.util.Date;
import java.util.List;

import com.graduation.education.course.service.dao.impl.mapper.entity.Adv;
import com.graduation.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.graduation.education.util.base.Page;

public interface AdvDao {
	int save(Adv record);

	int deleteById(Long id);

	int updateById(Adv record);

	Adv getById(Long id);

	Page<Adv> listForPage(int pageCurrent, int pageSize, AdvExample example);

	/**
	 * 列出广告信息，注意：开始时间<现在时间<结束时间
	 *
	 * @param platShow
	 * @param statusId
	 */
	List<Adv> listByPlatShowAndStatusIdAndBeginTimeAndEndTime(Integer platShow, Integer statusId, Date beginTime, Date endTime);
}