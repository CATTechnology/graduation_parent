package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.common.dto.WebsiteLinkDTO;
import com.graduation.education.system.common.dto.WebsiteLinkListDTO;
import com.graduation.education.system.service.dao.WebsiteLinkDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.StatusIdEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteLinkBiz {

	@Autowired
	private WebsiteLinkDao dao;

	public Result<WebsiteLinkListDTO> list() {
		List<WebsiteLink> websiteLinkList = dao.listByStatusId(StatusIdEnum.YES.getCode());
		WebsiteLinkListDTO dto = new WebsiteLinkListDTO();
		dto.setWebsiteLinkList(PageUtil.copyList(websiteLinkList, WebsiteLinkDTO.class));
		return Result.success(dto);
	}

}
