package com.graduation.education.system.service.api.auth;

import com.graduation.education.system.common.bo.MsgViewBO;
import com.graduation.education.system.common.dto.MsgDTO;
import com.graduation.education.system.service.api.auth.biz.AuthApiMsgBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/system/auth/msg")
public class AuthApiMsg extends BaseController {

	@Autowired
	private AuthApiMsgBiz biz;

	/**
	 * 课站内信详情
	 */
	@ApiOperation(value = "站内信详情", notes = "站内信详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	Result<MsgDTO> view(@RequestBody MsgViewBO MsgViewBO) {
		return biz.view(MsgViewBO);
	};
}
