package com.graduation.education.system.service.api;

import com.graduation.education.system.service.api.biz.ApiSysRoleBiz;
import com.graduation.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息
 *
 * @author wujing
 */
@RestController
public class ApiSysRoleController extends BaseController {

	@Autowired
	private ApiSysRoleBiz biz;

}
