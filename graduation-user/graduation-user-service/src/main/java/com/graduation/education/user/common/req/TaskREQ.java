package com.graduation.education.user.common.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/7 16:10
 */
@Data
public class TaskREQ implements Serializable {
    /**
     * 班级
     */
    @NotBlank
    private String classNo;

    /**
     * 学院名称
     */
    @NotBlank
    private String college;

    /**
     * 专业
     */
    @NotBlank
    private String professional;

    private String firstTask;

    private String secondTask;
}
