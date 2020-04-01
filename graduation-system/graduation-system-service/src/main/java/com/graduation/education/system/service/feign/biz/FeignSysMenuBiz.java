package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.SysMenuQO;
import com.graduation.education.system.feign.vo.SysMenuVO;
import com.graduation.education.system.service.dao.SysMenuDao;
import com.graduation.education.system.service.dao.SysMenuRoleDao;
import com.graduation.education.system.service.dao.SysRoleUserDao;
import com.graduation.education.system.service.dao.SysUserDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.*;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysMenuExample.Criteria;
import com.graduation.education.util.base.BaseBiz;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
public class FeignSysMenuBiz extends BaseBiz {

    @Autowired
    private SysMenuDao dao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public Page<SysMenuVO> listForPage(SysMenuQO qo) {
        SysMenuExample example = new SysMenuExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysMenu> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysMenuVO.class);
    }

    public int save(SysMenuQO qo) {
        SysMenu record = BeanUtil.copyProperties(qo, SysMenu.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public SysMenuVO getById(Long id) {
        SysMenu record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysMenuVO.class);
    }

    public int updateById(SysMenuQO qo) {
        SysMenu record = BeanUtil.copyProperties(qo, SysMenu.class);
        return dao.updateById(record);
    }

    public List<String> listByUserAndMenu(Long userNo) {
        List<String> list = new ArrayList<>();
        if (userNo == null) {
            // 没权限
            return list;
        }
        SysUser sysUser = sysUserDao.getByUserNo(userNo);
        if (ObjectUtil.isNull(sysUser)) {
            // 没权限
            return list;
        }
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for (SysRoleUser sru : sysRoleUserList) {
            List<SysMenuRole> list1 = sysMenuRoleDao.listByRoleId(sru.getRoleId());
            for (SysMenuRole sysMenuRole : list1) {
                SysMenu sysMenu = dao.getById(sysMenuRole.getMenuId());
                if (ObjectUtil.isNotNull(sysMenu)) {
                    list.add(sysMenu.getApiUrl());
                }
            }
        }

        return list;
    }
}
