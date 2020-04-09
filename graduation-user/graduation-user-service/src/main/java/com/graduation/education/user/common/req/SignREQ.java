package com.graduation.education.user.common.req;

import lombok.Data;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/4 14:25
 */
@Data
public class SignREQ {
    /**
     * 手机品牌
     */
    protected String phoneBrand;
    /**
     * 手机型号
     */
    protected String phoneModel;
    /**
     * 地理位置
     */
    protected String address;
    /**
     * 坐标信息
     */
    protected String point;
    /**
     * 坐标类型
     */
    protected String coordType;
}
