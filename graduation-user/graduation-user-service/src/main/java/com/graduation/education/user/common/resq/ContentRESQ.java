package com.graduation.education.user.common.resq;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/9 19:52
 */
@Data
@Builder
public class ContentRESQ implements Serializable {

    /**
     * 点击量
     */
    Long clickNum;

    /**
     * 访问人数（不存在重复人数）
     */
    Long visitNum;

    /**
     * 内容
     */
    String content;
}