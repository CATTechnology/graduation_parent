package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * @author 
 * 
 */
@Data
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 消息code
     */
    private String code;

    /**
     * 老师id
     */
    @NotBlank
    private Long teacherId;

    /**
     * 老师姓名
     */
    @NotBlank
    private String teacherName;

    /**
     * 消息标题
     */
    @NotBlank
    private String title;

    /**
     * 通知级别(普通、一般、重要、非常重要)
     */
    @NotBlank
    private String rank;

    /**
     * 任务开始时间
     */
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 任务结束时间
     */
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 消息被查看的人数
     */
    private Long lookTimes;

    /**
     * 提交方式
     */
    @NotBlank
    private String submission;

    /**
     * 当前消息所属的班级
     */
    @NotBlank
    private String classNo;

    /**
     * 提交地点
     */
    @NotBlank
    private String submissionAddr;

    /**
     * 消息创建时间
     */
    private Date createTime;

    /**
     * 消息更新时间
     */
    private Date modifyTine;

    /**
     * 消息内容
     */
    private String content;
}