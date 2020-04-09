package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Task implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 级别名称
     */
    private String name;

    /**
     * 父级
     */
    private Long parentId;

    /**
     * 发布者
     */
    private String author;

    /**
     * 所属的班级
     */
    private String classNo;

    private String professional;

    /**
     * 所属学院
     */
    private String college;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}