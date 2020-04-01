package com.graduation.education.system.service.api.pc;

import com.graduation.education.system.common.req.SysLogPageREQ;
import com.graduation.education.system.common.resq.SysLogPageRESQ;
import com.graduation.education.system.service.api.pc.biz.PcApiSysLogBiz;
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
 * 后台操作日志表
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/pc/sys/log")
public class PcApiSysLogController extends BaseController {

	@Autowired
	private PcApiSysLogBiz biz;

	/**
	 * 后台操作日志分页列表接口
	 */
	@ApiOperation(value = "后台操作日志分页列表接口", notes = "后台操作日志分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<SysLogPageRESQ>> list(@RequestBody SysLogPageREQ sysLogPageREQ) {
		return biz.list(sysLogPageREQ);
	}

}
