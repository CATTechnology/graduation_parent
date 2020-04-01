package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignSysUser;
import com.graduation.education.system.feign.qo.SysUserQO;
import com.graduation.education.system.feign.vo.SysUserVO;
import com.graduation.education.system.service.feign.biz.FeignSysUserBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户信息
 *
 */
@RestController
public class FeignSysUserController extends BaseController implements IFeignSysUser {

    @Autowired
    private FeignSysUserBiz biz;

    @Override
    public Page<SysUserVO> listForPage(@RequestBody SysUserQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysUserQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysUserQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysUserVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
