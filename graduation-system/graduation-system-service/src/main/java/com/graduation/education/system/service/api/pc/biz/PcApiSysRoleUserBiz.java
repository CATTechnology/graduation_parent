package com.graduation.education.system.service.api.pc.biz;

import com.graduation.education.system.common.req.SysRoleUserListREQ;
import com.graduation.education.system.common.req.SysRoleUserSaveREQ;
import com.graduation.education.system.common.resq.SysRoleUserListRESQ;
import com.graduation.education.system.common.resq.SysRoleUserRESQ;
import com.graduation.education.system.service.dao.SysRoleDao;
import com.graduation.education.system.service.dao.SysRoleUserDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRole;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUser;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.tools.BeanUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class PcApiSysRoleUserBiz {

	@Autowired
	private SysRoleUserDao dao;
	@Autowired
	private SysRoleDao sysRoleDao;

	public Result<SysRoleUserListRESQ> list(SysRoleUserListREQ req) {
		if (req.getUserId() == null) {
			return Result.error("用户ID不能为空");
		}
		SysRoleUserListRESQ resq = new SysRoleUserListRESQ();
		List<SysRoleUser> list = dao.listByUserId(req.getUserId());
		if (CollectionUtils.isNotEmpty(list)) {
			List<SysRoleUserRESQ> roleList = new ArrayList<>();
			for (SysRoleUser sysRoleUser : list) {
				SysRole sysRole = sysRoleDao.getById(sysRoleUser.getRoleId());
				roleList.add(BeanUtil.copyProperties(sysRole, SysRoleUserRESQ.class));
			}
			resq.setList(roleList);
		}
		return Result.success(resq);
	}

	@Transactional
	public Result<Integer> save(SysRoleUserSaveREQ req) {
		if (req.getUserId() == null) {
			return Result.error("用户ID不能为空");
		}
		// 先删除旧的，再添加新的
		dao.deleteByUserId(req.getUserId());
		if (CollectionUtils.isNotEmpty(req.getRoleId())) {
			// 拆分角色和平台拼接ID
			for (Long roleId : req.getRoleId()) {
				SysRoleUser sysRoleUser = new SysRoleUser();
				sysRoleUser.setRoleId(roleId);
				sysRoleUser.setUserId(req.getUserId());
				dao.save(sysRoleUser);
			}
		}
		return Result.success(1);
	}
}
