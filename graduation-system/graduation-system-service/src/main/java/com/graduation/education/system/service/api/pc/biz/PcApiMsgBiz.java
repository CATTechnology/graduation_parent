package com.graduation.education.system.service.api.pc.biz;

import com.aliyun.oas.utils.StringUtil;
import com.graduation.education.system.common.CacheRedis;
import com.graduation.education.system.common.req.*;
import com.graduation.education.system.common.resq.MsgPageRESQ;
import com.graduation.education.system.common.resq.MsgViewRESQ;
import com.graduation.education.system.feign.vo.MsgPushVO;
import com.graduation.education.system.service.dao.MsgDao;
import com.graduation.education.system.service.dao.MsgUserDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Msg;
import com.graduation.education.system.service.dao.impl.mapper.entity.MsgExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.MsgExample.Criteria;
import com.graduation.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.graduation.education.user.feign.interfaces.IFeignUserExt;
import com.graduation.education.user.feign.vo.UserExtMsgVO;
import com.graduation.education.util.base.BaseBiz;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.HasNoticeEnum;
import com.graduation.education.util.enums.RedisPreEnum;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.enums.StatusIdEnum;
import com.graduation.education.util.tools.BeanUtil;
import com.graduation.education.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 站内信消息
 */
@Component
public class PcApiMsgBiz extends BaseBiz {

	@Autowired
	private MsgDao dao;
	@Autowired
	private MsgUserDao msgUserDao;

	@Autowired
	private IFeignUserExt bossUserExt;

	@Autowired
	private CacheRedis cacheRedis;

	public Result<Page<MsgPageRESQ>> list(MsgPageREQ req) {
		MsgExample example = new MsgExample();
		Criteria c = example.createCriteria();
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		} else {
			c.andStatusIdLessThan(Constants.FREEZE);
		}
		if (StringUtil.isNotEmpty(req.getMsgTitle())) {
			c.andMsgTitleLike(PageUtil.like(req.getMsgTitle()));
		}
		if (req.getIsSend() != null) {
			c.andIsSendEqualTo(req.getIsSend());
		}
		if (req.getIsTop() != null) {
			c.andIsTopEqualTo(req.getIsTop());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Msg> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, MsgPageRESQ.class));
	}

	public Result<Integer> save(MsgSaveREQ req) {
		if (StringUtils.isEmpty(req.getMsgTitle())) {
			return Result.error("短信标题不能为空");
		}
		Msg record = BeanUtil.copyProperties(req, Msg.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	@Transactional
	public Result<Integer> delete(MsgDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		msgUserDao.deleteByMsgId(req.getId());
		int result = dao.deleteById(req.getId());
		if (result < 0) {
			return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
		}
		return Result.success(result);
	}

	public Result<Integer> update(MsgUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		if (StringUtils.isEmpty(req.getMsgTitle())) {
			return Result.error("短信标题不能为空");
		}
		Msg record = BeanUtil.copyProperties(req, Msg.class);
		int result = dao.updateById(record);
		if (result < 0) {
			return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
		}
		return Result.success(result);
	}

	public Result<MsgViewRESQ> view(MsgViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Msg msg = dao.getById(req.getId());
		if (ObjectUtil.isNull(msg)) {
			return Result.error("找不到消息信息");
		}
		MsgViewRESQ record = BeanUtil.copyProperties(msg, MsgViewRESQ.class);
		return Result.success(record);
	}

	public Result<Integer> push(MsgPushREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		// 获得模板
		Msg msg = dao.getById(req.getId());
		if (ObjectUtil.isNull(msg)) {
			return Result.error("查找msg失败");
		}
		final MsgPushVO msgPush = BeanUtil.copyProperties(msg, MsgPushVO.class);
		// 刷新站内信
		Msg msgNew = new Msg();
		msgNew.setId(req.getId());
		msgNew.setIsSend(HasNoticeEnum.YES.getCode());
		int result = dao.updateById(msgNew);
		if (result < 0) {
			return Result.error("发送失败");
		}
		callbackExecutor.execute(new Runnable() {
			@Override
			public void run() {
				pushToUserByMsgPush(msgPush);
			}
		});
		return Result.success(result);
	}

	private void pushToUserByMsgPush(MsgPushVO msgPush) {
		// 获取缓存的条数
		int num = getCacheNum();
		for (int i = 1; i < num + 1; i++) {
			List<UserExtMsgVO> list = cacheRedis.list(RedisPreEnum.SYS_MSG_SEND.getCode() + "_" + i, UserExtMsgVO.class);
			if (CollectionUtil.isNotEmpty(list)) {
				// 批量生成
				for (UserExtMsgVO vo : list) {
					saveMsgUser(msgPush, vo);
				}
			}
		}
	}

	private int getCacheNum() {
		boolean flag = cacheRedis.hasKey(RedisPreEnum.SYS_MSG_SEND_NUM.getCode());
		if (!flag) {// 找不到，去缓存用户信息
			bossUserExt.cachUserForMsg();
		}
		int num = cacheRedis.get(RedisPreEnum.SYS_MSG_SEND_NUM.getCode(), int.class);
		return num;
	}

	private void saveMsgUser(MsgPushVO msg, UserExtMsgVO vo) {
		MsgUser record = new MsgUser();
		record.setStatusId(StatusIdEnum.YES.getCode());
		record.setMsgId(msg.getId());
		record.setMsgTitle(msg.getMsgTitle());
		record.setUserNo(vo.getUserNo());
		record.setMobile(vo.getMobile());
		msgUserDao.save(record);
	}

}
