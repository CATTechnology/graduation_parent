package com.graduation.education.user.common.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/4 14:25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentSignREQ extends SignREQ{

    private String studentNo;
    private String studentName;
    private String courseName;
    private String classNo;
    private String courseCode;
}
