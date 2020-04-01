package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.WebsiteQO;
import com.graduation.education.system.feign.vo.WebsiteVO;
import com.graduation.education.system.service.dao.WebsiteDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Website;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteBiz {

	@Autowired
	private WebsiteDao dao;

	public Page<WebsiteVO> listForPage(WebsiteQO qo) {
		WebsiteExample example = new WebsiteExample();
		example.setOrderByClause(" id desc ");
		Page<Website> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, WebsiteVO.class);
	}

	public int save(WebsiteQO qo) {
		Website record = BeanUtil.copyProperties(qo, Website.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public WebsiteVO getById(Long id) {
		Website record = dao.getById(id);
		return BeanUtil.copyProperties(record, WebsiteVO.class);
	}

	public int updateById(WebsiteQO qo) {
		Website record = BeanUtil.copyProperties(qo, Website.class);
		return dao.updateById(record);
	}

	public WebsiteVO getWebsite() {
		Website website = dao.getWebsite();
		return BeanUtil.copyProperties(website, WebsiteVO.class);
	}

}
