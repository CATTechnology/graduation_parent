/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 */
@Getter
@AllArgsConstructor
public enum StatusIdEnum {

	YES(1, "正常", ""), NO(0, "禁用", "red");

	private Integer code;

	private String desc;

	private String color;

}
