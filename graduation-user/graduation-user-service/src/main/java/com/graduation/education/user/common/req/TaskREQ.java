package com.graduation.education.user.common.req;

import lombok.Data;

import javax.validation.constraints.Min;
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
     * 当前页数
     */
    @Min(value = 1)
    private Integer page = 1;

    /**
     * 当前页数个数
     */
    @Min(value = 1)
    private Integer size = Integer.MAX_VALUE;

    /**
     * 班级
     */
    @NotBlank
    private String classNo = "1602";

    /**
     * 学院名称
     */
    @NotBlank
    private String college = "信息与计算科学";

    /**
     * 专业
     */
    @NotBlank
    private String professional = "计算机与信息科学";

    /**
     * 一级任务名称
     */
    private String firstTask;

    /**
     * 二级任务名称
     */
    private String secondTask;
}
