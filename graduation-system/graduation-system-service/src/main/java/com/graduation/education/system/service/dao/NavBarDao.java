package com.graduation.education.system.service.dao;

import com.graduation.education.system.service.dao.impl.mapper.entity.NavBar;
import com.graduation.education.system.service.dao.impl.mapper.entity.NavBarExample;
import com.graduation.education.util.base.Page;

import java.util.List;

public interface NavBarDao {
	int save(NavBar record);

	int deleteById(Long id);

	int updateById(NavBar record);

	NavBar getById(Long id);

	Page<NavBar> listForPage(int pageCurrent, int pageSize, NavBarExample example);

	/**
	 * 根据导航链接查找信息
	 * 
	 * @param navUrl
	 * @return
	 * @author wuyun
	 */
	NavBar getByNavUrl(String navUrl);

	/**
	 * 查找可用的头部导航
	 * 
	 * @param statusId
	 * @return
	 * @author wuyun
	 */
	List<NavBar> getByStatusId(Integer statusId);
}