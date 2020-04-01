package com.graduation.education.user.service.dao;

import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerProfit;
import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerProfitExample;
import com.graduation.education.util.base.Page;

public interface LecturerProfitDao {
	int save(LecturerProfit record);

	int deleteById(Long id);

	int updateById(LecturerProfit record);

	LecturerProfit getById(Long id);

	Page<LecturerProfit> listForPage(int pageCurrent, int pageSize, LecturerProfitExample example);
}