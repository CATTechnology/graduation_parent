package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.common.dto.WebsiteDTO;
import com.graduation.education.system.service.dao.WebsiteDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Website;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.StatusIdEnum;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteBiz {

	@Autowired
	private WebsiteDao websitedao;

	public Result<WebsiteDTO> get() {
		Website website = websitedao.getByStatusId(StatusIdEnum.YES.getCode());
		WebsiteDTO dto = BeanUtil.copyProperties(website, WebsiteDTO.class);
		if (StringUtils.hasText(dto.getPrn())) {
			// 公安网备案号处理
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(dto.getPrn());
			dto.setPrnNo(m.replaceAll("").trim());
		}
		return Result.success(dto);
	}

}
