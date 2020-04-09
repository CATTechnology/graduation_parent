package com.graduation.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 戴灵飞
 * 
 */
@Data
public class Advice implements Serializable {
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
    private Long teacherId;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息被查看的人数
     */
    private Long lookTimes;

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

    private static final long serialVersionUID = 1L;
}