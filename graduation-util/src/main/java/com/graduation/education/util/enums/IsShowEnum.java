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
public enum IsShowEnum {

    YES(1, "显示"), NO(0, "不显示");

    private Integer code;

    private String desc;

}
