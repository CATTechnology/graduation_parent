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
public enum AdvTargetEnum {

	BLANK("_blank", "新窗口打开"), SELF("_self", "同窗口打开");

	private String code;

	private String desc;

}
