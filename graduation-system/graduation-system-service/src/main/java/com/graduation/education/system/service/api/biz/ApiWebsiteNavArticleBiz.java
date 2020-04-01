package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.common.bo.WebsiteNavArticleBO;
import com.graduation.education.system.common.dto.WebsiteNavArticleDTO;
import com.graduation.education.system.service.dao.WebsiteNavArticleDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteNavArticle;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.StatusIdEnum;
import com.graduation.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteNavArticleBiz {

	@Autowired
	private WebsiteNavArticleDao dao;

	public Result<WebsiteNavArticleDTO> get(WebsiteNavArticleBO websiteNavArticleBO) {
		if (StringUtils.isEmpty(websiteNavArticleBO.getNavId())) {
			return Result.error("navId不能为空");
		}
		WebsiteNavArticle websiteNavArticle = dao.getByNavIdAndStatusId(websiteNavArticleBO.getNavId(), StatusIdEnum.YES.getCode());
		if (ObjectUtil.isNull(websiteNavArticle)) {
			return Result.error("没有找到站点导航文章信息");
		}
		return Result.success(BeanUtil.copyProperties(websiteNavArticle, WebsiteNavArticleDTO.class));
	}
}
