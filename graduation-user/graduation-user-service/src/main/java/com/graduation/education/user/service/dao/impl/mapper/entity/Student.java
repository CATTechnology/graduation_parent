package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 * 学生信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student extends User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 班级
     */
    private String classNo;

    /**
     * 专业
     */
    private String professional;

    /**
     * 学院
     */
    private String college;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 微信号
     */
    private String wetchat;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号
     */
    private String qq;

    /**
     * 密码（md5）
     */
    private String passwd;

    /**
     * 老师编号
     */
    private String studentNo;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfBirth;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 固定电话/短号
     */
    private String telephone;

    /**
     * 手机品牌
     */
    private String phoneBrand;

    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}