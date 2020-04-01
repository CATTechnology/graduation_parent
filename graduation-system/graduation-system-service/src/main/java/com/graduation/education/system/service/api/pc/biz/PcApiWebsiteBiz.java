package com.graduation.education.system.service.api.pc.biz;

import com.graduation.education.system.common.req.WebsiteUpdateREQ;
import com.graduation.education.system.common.resq.WebsiteViewRESQ;
import com.graduation.education.system.service.dao.WebsiteDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Website;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点信息
 *
 */
@Component
public class PcApiWebsiteBiz {

	@Autowired
	private WebsiteDao dao;

	public Result<WebsiteViewRESQ> view() {
		Website website = dao.getWebsite();
		return Result.success(BeanUtil.copyProperties(website, WebsiteViewRESQ.class));
	}

	public Result<Integer> update(WebsiteUpdateREQ req) {
		Website record = BeanUtil.copyProperties(req, Website.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

}
