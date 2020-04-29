package com.graduation.education.user.common.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/27 16:35
 */
@Data
public class StudentREQ extends Page implements Serializable {

    /**
     * 昵称
     */
    protected String name;

    /**
     * 真实姓名
     */
    protected String realName;

    /**
     * 编号
     */
    protected String classNo;

    /**
     * 学号
     */
    protected String studentNo;

    /**
     * telephone电话
     */
    protected String mobile;

    /**
     * 专业
     */
    protected String professional;

    /**
     * 学院
     */
    protected String college;

}
