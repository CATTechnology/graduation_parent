package com.graduation.education.system.common.resq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息-列出
 *
 */
@Data
@Accessors(chain = true)
public class SysMenuUserListRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单集合
	 */
	@ApiModelProperty(value = "菜单集合")
	private List<SysMenuUserRESQ> sysMenu = new ArrayList<>();

}
