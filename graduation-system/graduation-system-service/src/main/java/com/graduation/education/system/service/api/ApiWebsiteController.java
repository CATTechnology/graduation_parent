package com.graduation.education.system.service.api;

import com.graduation.education.system.common.dto.WebsiteDTO;
import com.graduation.education.system.service.api.biz.ApiWebsiteBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteController extends BaseController {

	@Autowired
	private ApiWebsiteBiz biz;

	/**
	 * 获取站点信息接口
	 *
	 * @return 站点信息
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
	@RequestMapping(value = "/system/api/website/get", method = RequestMethod.POST)
	public Result<WebsiteDTO> get() {
		return biz.get();
	}

}
