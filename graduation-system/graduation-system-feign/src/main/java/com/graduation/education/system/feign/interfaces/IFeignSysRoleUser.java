package com.graduation.education.system.feign.interfaces;

import com.graduation.education.system.feign.qo.SysRoleUserQO;
import com.graduation.education.system.feign.vo.SysRoleUserVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色用户关联表
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysRoleUser {


    @RequestMapping(value = "/feign/system/sysRoleUser/listForPage")
    Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/save")
    int save(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysRoleUser/updateById")
    int updateById(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/getById")
    SysRoleUserVO getById(@RequestBody Long id);

}
