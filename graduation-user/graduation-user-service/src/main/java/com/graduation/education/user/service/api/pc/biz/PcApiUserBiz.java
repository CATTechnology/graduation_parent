package com.graduation.education.user.service.api.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.graduation.education.user.common.req.UserPageREQ;
import com.graduation.education.user.common.resq.UserPageRESQ;
import com.graduation.education.user.service.dao.UserDao;
import com.graduation.education.user.service.dao.impl.mapper.entity.User;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;

/**
 * 用户信息
 */
@Component
public class PcApiUserBiz {

	@Autowired
	private UserDao dao;

	/**
	 * 用戶分页列出
	 *
	 * @param userPageREQ
	 * @return
	 */
	public Result<Page<UserPageRESQ>> listForPage(UserPageREQ req) {
		UserExample example = new UserExample();
		UserExample.Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getMobile())) {
			c.andMobileLike(PageUtil.like(req.getMobile()));
		}
		example.setOrderByClause(" status_id desc, id desc ");
		Page<User> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, UserPageRESQ.class));
	}
}
