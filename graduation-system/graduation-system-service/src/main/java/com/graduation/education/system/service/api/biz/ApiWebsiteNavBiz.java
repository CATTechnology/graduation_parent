package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.common.dto.WebsiteNavDTO;
import com.graduation.education.system.common.dto.WebsiteNavListDTO;
import com.graduation.education.system.service.dao.WebsiteNavDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteNav;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.StatusIdEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteNavBiz {

	@Autowired
	private WebsiteNavDao websiteNavDao;

	public Result<WebsiteNavListDTO> list() {
		WebsiteNavListDTO dto = new WebsiteNavListDTO();
		List<WebsiteNav> list = websiteNavDao.listByStatusIdAndParentId(StatusIdEnum.YES.getCode(), 0L);
		if (CollectionUtils.isNotEmpty(list)) {
			List<WebsiteNavDTO> websiteNavList = PageUtil.copyList(list, WebsiteNavDTO.class);
			for (WebsiteNavDTO webSiteNav : websiteNavList) {
				list = websiteNavDao.listByStatusIdAndParentId(StatusIdEnum.YES.getCode(), webSiteNav.getId());
				webSiteNav.setWebsiteNavList(PageUtil.copyList(list, WebsiteNavDTO.class));
			}
			dto.setWebsiteNavList(websiteNavList);
		}
		return Result.success(dto);
	}

}
