package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.SysRoleQO;
import com.graduation.education.system.feign.vo.SysRoleVO;
import com.graduation.education.system.service.dao.SysRoleDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRole;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色信息
 *
 * @author wujing
 */
@Component
public class FeignSysRoleBiz {

	@Autowired
	private SysRoleDao dao;

	public Page<SysRoleVO> listForPage(SysRoleQO qo) {
	    SysRoleExample example = new SysRoleExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<SysRole> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysRoleVO.class);
	}

	public int save(SysRoleQO qo) {
        SysRole record = BeanUtil.copyProperties(qo, SysRole.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysRoleVO getById(Long id) {
	    SysRole record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysRoleVO.class);
	}

	public int updateById(SysRoleQO qo) {
	    SysRole record = BeanUtil.copyProperties(qo, SysRole.class);
		return dao.updateById(record);
	}

}
