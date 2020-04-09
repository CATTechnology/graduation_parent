package com.graduation.education.user.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/4 19:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentLoginDto extends UserLoginDTO {

    /**
     * 学号
     */
    private String studentNo;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 班级号
     */
    private String classNo;
    /**
     * 专业
     */
    private String professional;
    /**
     * 学院名称
     */
    private String college;
}
