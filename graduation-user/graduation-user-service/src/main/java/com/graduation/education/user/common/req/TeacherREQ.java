package com.graduation.education.user.common.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/27 16:35
 */
@Data
public class TeacherREQ extends Page implements Serializable {

    /**
     * 昵称
     */
    protected String name;

    /**
     * 编号
     */
    protected String number;

    /**
     * 真实姓名
     */
    protected String realName;

    /**
     * mobile
     */
    protected String mobile;

}
