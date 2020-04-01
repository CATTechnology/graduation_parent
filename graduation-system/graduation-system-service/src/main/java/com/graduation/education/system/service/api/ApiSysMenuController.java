package com.graduation.education.system.service.api;

import com.graduation.education.system.service.api.biz.ApiSysMenuBiz;
import com.graduation.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单信息
 *
 * @author wujing
 */
@RestController
public class ApiSysMenuController extends BaseController {

	@Autowired
	private ApiSysMenuBiz biz;

}
