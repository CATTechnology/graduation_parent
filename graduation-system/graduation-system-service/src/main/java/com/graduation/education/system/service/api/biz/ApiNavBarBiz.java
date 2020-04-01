package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.common.dto.NavBarDTO;
import com.graduation.education.system.common.dto.NavBarListDTO;
import com.graduation.education.system.service.dao.NavBarDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.NavBar;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.StatusIdEnum;
import com.graduation.education.util.tools.ArrayListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Component
public class ApiNavBarBiz {

	@Autowired
	private NavBarDao navBarDao;

	public Result<NavBarListDTO> list() {
		List<NavBar> list = navBarDao.getByStatusId(StatusIdEnum.YES.getCode());
		NavBarListDTO dto = new NavBarListDTO();
		dto.setList(ArrayListUtil.copy(list, NavBarDTO.class));
		return Result.success(dto);
	}

}
