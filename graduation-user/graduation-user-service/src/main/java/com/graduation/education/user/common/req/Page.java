package com.graduation.education.user.common.req;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/27 16:37
 */
@Data
public class Page {

    @Min(value = 1L)
    protected int page;

    @Min(value = 1L)
    protected int size;
}
