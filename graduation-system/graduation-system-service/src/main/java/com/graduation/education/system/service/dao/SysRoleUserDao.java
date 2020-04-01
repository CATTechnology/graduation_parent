package com.graduation.education.system.service.dao;

import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUser;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUserExample;
import com.graduation.education.util.base.Page;

import java.util.List;

public interface SysRoleUserDao {
	int save(SysRoleUser record);

	int deleteById(Long id);

	int updateById(SysRoleUser record);

	int updateByExampleSelective(SysRoleUser record, SysRoleUserExample example);

	SysRoleUser getById(Long id);

	Page<SysRoleUser> listForPage(int pageCurrent, int pageSize, SysRoleUserExample example);

	/**
	 * 删除用户所有角色
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);

	/**
	 * 列出用户所有角色
	 * 
	 * @param userId
	 * @return
	 */
	List<SysRoleUser> listByUserId(Long userId);
}