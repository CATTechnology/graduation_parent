package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.SysUserQO;
import com.graduation.education.system.feign.vo.SysUserVO;
import com.graduation.education.system.service.dao.SysUserDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysUser;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysUserExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Component
public class FeignSysUserBiz {

	@Autowired
	private SysUserDao dao;

	public Page<SysUserVO> listForPage(SysUserQO qo) {
		SysUserExample example = new SysUserExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysUserVO.class);
	}

	public int save(SysUserQO qo) {
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysUserVO getById(Long id) {
		SysUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysUserVO.class);
	}

	public int updateById(SysUserQO qo) {
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.updateById(record);
	}

}
