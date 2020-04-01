package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignSysRoleUser;
import com.graduation.education.system.feign.qo.SysRoleUserQO;
import com.graduation.education.system.feign.vo.SysRoleUserVO;
import com.graduation.education.system.service.feign.biz.FeignSysRoleUserBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色用户关联表
 *
 */
@RestController
public class FeignSysRoleUserController extends BaseController implements IFeignSysRoleUser {

    @Autowired
    private FeignSysRoleUserBiz biz;

    @Override
    public Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysRoleUserQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysRoleUserQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysRoleUserVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
