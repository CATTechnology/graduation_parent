package com.graduation.education.user.common.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/14 16:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentRegisterBO extends UserRegisterBO implements Serializable {

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
     * 邮箱
     */
    private String email;

    /**
     * qq号
     */
    private String qq;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 出生日期
     */
    private Date dateOfBirth;

    /**
     * 手机品牌
     */
    private String phoneBrand;

    /**
     * 手机型号
     */
    private String phoneModel;

}
