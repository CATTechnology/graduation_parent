package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignSysRole;
import com.graduation.education.system.feign.qo.SysRoleQO;
import com.graduation.education.system.feign.vo.SysRoleVO;
import com.graduation.education.system.service.feign.biz.FeignSysRoleBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息
 *
 */
@RestController
public class FeignSysRoleController extends BaseController implements IFeignSysRole {

    @Autowired
    private FeignSysRoleBiz biz;

    @Override
    public Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysRoleQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysRoleQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysRoleVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
