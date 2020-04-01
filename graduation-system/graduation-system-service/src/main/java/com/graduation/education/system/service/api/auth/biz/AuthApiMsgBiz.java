package com.graduation.education.system.service.api.auth.biz;

import com.graduation.education.system.common.bo.MsgViewBO;
import com.graduation.education.system.common.dto.MsgDTO;
import com.graduation.education.system.service.dao.MsgDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Msg;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class AuthApiMsgBiz {

	@Autowired
	private MsgDao dao;

	public Result<MsgDTO> view(MsgViewBO bo) {
		if (bo.getId() == null) {
			return Result.error("id不能为空");
		}
		Msg msg = dao.getById(bo.getId());
		if (ObjectUtils.isEmpty(msg)) {
			return Result.error("查询错误");
		}
		return Result.success(BeanUtil.copyProperties(msg, MsgDTO.class));
	}
}
