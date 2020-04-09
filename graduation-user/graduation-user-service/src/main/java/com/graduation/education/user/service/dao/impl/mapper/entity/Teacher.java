package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 老师信息表
 */
@Data
public class Teacher extends User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 密码（md5）
     */
    private String passwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 老师编号
     */
    private String number;

    /**
     * 出生日期
     */
    private Date dateOfBirth;

    /**
     * 固定电话/短号
     */
    private String telephone;

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