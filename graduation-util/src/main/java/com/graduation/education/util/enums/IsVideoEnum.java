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
public enum IsVideoEnum {

    YES("1", "存在"), NO("0", "否");

    private String code;

    private String desc;

}
