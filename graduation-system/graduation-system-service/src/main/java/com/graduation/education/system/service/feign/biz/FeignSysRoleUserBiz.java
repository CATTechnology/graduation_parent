package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.SysRoleUserQO;
import com.graduation.education.system.feign.vo.SysRoleUserVO;
import com.graduation.education.system.service.dao.SysRoleUserDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUser;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUserExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysRoleUserExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class FeignSysRoleUserBiz {

	@Autowired
	private SysRoleUserDao dao;

	public Page<SysRoleUserVO> listForPage(SysRoleUserQO qo) {
	    SysRoleUserExample example = new SysRoleUserExample();
	    Criteria c = example.createCriteria();
	    example.setOrderByClause(" id desc ");
        Page<SysRoleUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysRoleUserVO.class);
	}

	public int save(SysRoleUserQO qo) {
        SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysRoleUserVO getById(Long id) {
	    SysRoleUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysRoleUserVO.class);
	}

	public int updateById(SysRoleUserQO qo) {
	    SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
		return dao.updateById(record);
	}

}
