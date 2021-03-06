package com.graduation.education.system.service.api.pc;

import com.graduation.education.system.common.req.*;
import com.graduation.education.system.common.resq.SysRolePageRESQ;
import com.graduation.education.system.common.resq.SysRoleViewRESQ;
import com.graduation.education.system.service.api.pc.biz.PcApiSysRoleBiz;
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
 * 角色信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/pc/sys/role")
public class PcApiSysRoleController extends BaseController {

	@Autowired
	private PcApiSysRoleBiz biz;

	/**
	 * 角色分页列表接口
	 */
	@ApiOperation(value = "角色分页列表接口", notes = "角色分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<SysRolePageRESQ>> list(@RequestBody SysRolePageREQ sysRolePageREQ) {
		return biz.list(sysRolePageREQ);
	}

	/**
	 * 角色添加接口
	 */
	@ApiOperation(value = "角色添加接口", notes = "角色添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody SysRoleSaveREQ sysRoleSaveREQ) {
		return biz.save(sysRoleSaveREQ);
	}

	/**
	 * 角色删除接口
	 */
	@ApiOperation(value = "角色删除接口", notes = "角色删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody SysRoleDeleteREQ sysRoleDeleteREQ) {
		return biz.delete(sysRoleDeleteREQ);
	}

	/**
	 * 角色更新接口
	 */
	@ApiOperation(value = "角色更新接口", notes = "角色更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody SysRoleUpdateREQ sysRoleUpdateREQ) {
		return biz.update(sysRoleUpdateREQ);
	}

	/**
	 * 角色查看接口
	 */
	@ApiOperation(value = "角色查看接口", notes = "角色查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<SysRoleViewRESQ> view(@RequestBody SysRoleViewREQ sysRoleViewREQ) {
		return biz.view(sysRoleViewREQ);
	}

}
