package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class StudentSign implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 学生真实姓名
     */
    private String studentName;

    /**
     * 班级
     */
    private String classNo;

    /**
     * 手机品牌
     */
    private String phoneBrand;

    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * 地址
     */
    private String address;

    /**
     * 坐标
     */
    private String point;

    /**
     * 坐标类型
     */
    private String coordtype = "unknown";

    /**
     * 是否变更手机（1表示是 ， 0表示否）
     */
    private Byte changePhone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}