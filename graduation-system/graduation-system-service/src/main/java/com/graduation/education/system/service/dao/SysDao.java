package com.graduation.education.system.service.dao;

import com.graduation.education.system.service.dao.impl.mapper.entity.Sys;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysExample;
import com.graduation.education.util.base.Page;

public interface SysDao {
	int save(Sys record);

	int deleteById(Long id);

	int updateById(Sys record);

	Sys getById(Long id);

	Page<Sys> listForPage(int pageCurrent, int pageSize, SysExample example);

	/**
	 * 获得系统配置表信息
	 * 
	 * @return
	 * @author YZJ
	 */
	Sys getSys();
}