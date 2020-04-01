package com.graduation.education.system.service.api.pc.biz;

import com.graduation.education.system.common.req.*;
import com.graduation.education.system.common.resq.WebsiteLinkPageRESQ;
import com.graduation.education.system.common.resq.WebsiteLinkViewRESQ;
import com.graduation.education.system.service.dao.WebsiteLinkDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 站点友情链接
 */
@Component
public class PcApiWebsiteLinkBiz {

	@Autowired
	private WebsiteLinkDao dao;

	/**
	 * 分页列出
	 *
	 * @param req
	 * @return
	 */
	public Result<Page<WebsiteLinkPageRESQ>> list(WebsiteLinkPageREQ req) {
		WebsiteLinkExample example = new WebsiteLinkExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getLinkName())) {
			c.andLinkNameLike(PageUtil.like(req.getLinkName()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<WebsiteLink> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, WebsiteLinkPageRESQ.class));
	}

	/**
	 * 添加
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> save(WebsiteLinkSaveREQ req) {
		WebsiteLink record = BeanUtil.copyProperties(req, WebsiteLink.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 删除
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(WebsiteLinkDeleteREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		WebsiteLink websiteLink = dao.getById(req.getId());
		if (ObjectUtil.isNull(websiteLink)) {
			return Result.error("找不到友情链接信息");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
	}

	/**
	 * 更新
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> update(WebsiteLinkUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		WebsiteLink websiteLink = dao.getById(req.getId());
		if (ObjectUtil.isNull(websiteLink)) {
			return Result.error("找不到友情链接信息");
		}
		WebsiteLink record = BeanUtil.copyProperties(req, WebsiteLink.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
	}

	/**
	 * 查看
	 *
	 * @param req
	 * @return
	 */
	public Result<WebsiteLinkViewRESQ> view(WebsiteLinkViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		WebsiteLink record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到友情链接信息");
		}
		return Result.success(BeanUtil.copyProperties(record, WebsiteLinkViewRESQ.class));
	}

}
