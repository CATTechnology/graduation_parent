package com.graduation.education.system.service.api;

import com.graduation.education.system.common.dto.WebsiteNavListDTO;
import com.graduation.education.system.service.api.biz.ApiWebsiteNavBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteNavController extends BaseController {

	@Autowired
	private ApiWebsiteNavBiz biz;

	/**
	 * 获取站点导航信息接口
	 *
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点导航信息接口", notes = "获取站点导航信息")
	@RequestMapping(value = "/system/api/website/nav/list", method = RequestMethod.POST)
	public Result<WebsiteNavListDTO> list() {
		return biz.list();
	}

}
