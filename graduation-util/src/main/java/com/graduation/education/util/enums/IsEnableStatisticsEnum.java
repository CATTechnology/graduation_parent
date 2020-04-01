/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否开启统计
 * 
 */
@Getter
@AllArgsConstructor
public enum IsEnableStatisticsEnum {

	YES(1, "开启"), NO(0, "关闭");

	private Integer code;

	private String desc;

}
