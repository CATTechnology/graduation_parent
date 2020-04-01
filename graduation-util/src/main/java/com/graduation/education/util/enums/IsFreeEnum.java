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
public enum IsFreeEnum {

    FREE(1, "免费", ""), CHARGE(0, "收费", "green");

    private Integer code;

    private String desc;
    
    private String color;

}
