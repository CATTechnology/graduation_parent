package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long taskId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 任务介绍
     */
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
    private String content;

    private static final long serialVersionUID = 1L;
}