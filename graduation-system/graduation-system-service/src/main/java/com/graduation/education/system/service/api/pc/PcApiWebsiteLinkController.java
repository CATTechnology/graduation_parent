package com.graduation.education.system.service.api.pc;

import com.graduation.education.system.common.req.*;
import com.graduation.education.system.common.resq.WebsiteLinkPageRESQ;
import com.graduation.education.system.common.resq.WebsiteLinkViewRESQ;
import com.graduation.education.system.service.api.pc.biz.PcApiWebsiteLinkBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点友情链接
 */
@RestController
@RequestMapping(value = "/system/pc/website/link")
public class PcApiWebsiteLinkController extends BaseController {

	@Autowired
	private PcApiWebsiteLinkBiz biz;

	/**
	 * 站点友情分页列表接口
	 */
	@ApiOperation(value = "站点友情分页列表链接接口", notes = "站点友情分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<WebsiteLinkPageRESQ>> list(@RequestBody WebsiteLinkPageREQ websiteLinkPageREQ) {
		return biz.list(websiteLinkPageREQ);
	}

	/**
	 * 站点友情保存接口
	 */
	@ApiOperation(value = "站点友情保存接口", notes = "站点友情保存接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody WebsiteLinkSaveREQ websiteLinkSaveREQ) {
		return biz.save(websiteLinkSaveREQ);
	}

	/**
	 * 站点友情删除接口
	 */
	@ApiOperation(value = "站点友情删除接口", notes = "站点友情删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody WebsiteLinkDeleteREQ websiteLinkDeleteREQ) {
		return biz.delete(websiteLinkDeleteREQ);
	}

	/**
	 * 站点友情更新接口
	 */
	@ApiOperation(value = "站点友情更新接口", notes = "站点友情更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody WebsiteLinkUpdateREQ websiteLinkUpdateREQ) {
		return biz.update(websiteLinkUpdateREQ);
	}

	/**
	 * 站点友情查看接口
	 */
	@ApiOperation(value = "站点友情查看接口", notes = "站点友情查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<WebsiteLinkViewRESQ> view(@RequestBody WebsiteLinkViewREQ websiteLinkViewREQ) {
		return biz.view(websiteLinkViewREQ);
	}

}
