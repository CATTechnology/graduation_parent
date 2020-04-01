package com.graduation.education.course.service.api.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graduation.education.course.common.bo.AdvBO;
import com.graduation.education.course.common.dto.AdvDTO;
import com.graduation.education.course.common.dto.AdvListDTO;
import com.graduation.education.course.service.dao.AdvDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.Adv;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.tools.DateUtil;
import com.graduation.education.util.enums.StatusIdEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
public class ApiAdvBiz {

	@Autowired
	private AdvDao advDao;

	public Result<AdvListDTO> list(AdvBO advBO) {
		AdvListDTO dto = new AdvListDTO();
		// 开始时间和结束时间
		List<Adv> advList = advDao.listByPlatShowAndStatusIdAndBeginTimeAndEndTime(advBO.getPlatShow(), StatusIdEnum.YES.getCode(), DateUtil.parseDate("2019-07-04", "yyyy-MM-dd"), DateUtil.parseDate("2019-07-03", "yyyy-MM-dd"));
		if (CollectionUtil.isNotEmpty(advList)) {
			dto.setAdvList(PageUtil.copyList(advList, AdvDTO.class));
		}
		return Result.success(dto);
	}

}
