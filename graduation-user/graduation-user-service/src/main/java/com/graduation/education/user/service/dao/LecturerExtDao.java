package com.graduation.education.user.service.dao;

import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerExtExample;
import com.graduation.education.util.base.Page;

public interface LecturerExtDao {
	int save(LecturerExt record);

	int deleteById(Long id);

	int updateById(LecturerExt record);

	LecturerExt getById(Long id);

	Page<LecturerExt> listForPage(int pageCurrent, int pageSize, LecturerExtExample example);

	/**
	 * 根据传入讲师用户编号获取讲师账户信息
	 * 
	 * @param lecturerUserNo
	 * @return
	 */
	LecturerExt getByLecturerUserNo(Long lecturerUserNo);

	/**
	 * 根据传入讲师用户编号更新讲师账户信息
	 * 
	 * @param lecturerUserNo
	 * @return
	 * @author wuyun
	 */
	int updateByLecturerUserNo(LecturerExt record);

}