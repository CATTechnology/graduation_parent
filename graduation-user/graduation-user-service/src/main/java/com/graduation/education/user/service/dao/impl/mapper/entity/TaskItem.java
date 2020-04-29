package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 
 * 
 */
@Data
public class TaskItem implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 属于的任务体系
     */
    @Min(value = 1)
    private Long taskId;

    /**
     * 任务标题
     */
    @NotNull
    private String title;

    /**
     * 图片地址
     */
    @NotNull
    private String image;

    /**
     * 任务介绍
     */
    @NotNull
    private String introduce;

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

    /**
     * 详细内容
     */
    @NotNull
    private String content;

    @NotNull
    private String contentMd;

    private static final long serialVersionUID = 1L;
}