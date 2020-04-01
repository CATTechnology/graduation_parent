package com.graduation.education.system.service.api.pc;

import com.graduation.education.system.common.req.MsgUserDeleteREQ;
import com.graduation.education.system.common.req.MsgUserPageREQ;
import com.graduation.education.system.common.req.MsgUserViewREQ;
import com.graduation.education.system.common.resq.MsgUserPageRESQ;
import com.graduation.education.system.common.resq.MsgUserViewRESQ;
import com.graduation.education.system.service.api.pc.biz.PcApiMsgUserBiz;
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
 * 用户消息
 *
 */
@RestController
@RequestMapping(value = "/system/pc/msg/user")
public class PcApiMsgUserController extends BaseController {

	@Autowired
	private PcApiMsgUserBiz biz;

	/**
	 * 用户消息分页列表接口
	 */
	@ApiOperation(value = "用户消息分页列表接口", notes = "用户消息分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<MsgUserPageRESQ>> list(@RequestBody MsgUserPageREQ msgUserPageREQ) {
		return biz.list(msgUserPageREQ);
	}

	/**
	 * 用户消息删除接口
	 */
	@ApiOperation(value = "用户消息删除接口", notes = "用户消息删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody MsgUserDeleteREQ msgUserDeleteREQ) {
		return biz.delete(msgUserDeleteREQ);
	}

	/**
	 * 用户消息查看接口
	 */
	@ApiOperation(value = "用户消息查看接口", notes = "用户消息查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<MsgUserViewRESQ> view(@RequestBody MsgUserViewREQ msgUserViewREQ) {
		return biz.view(msgUserViewREQ);
	}
}
