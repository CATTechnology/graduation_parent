package com.graduation.education.course.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthCourseVideoBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID", required = true)
    private Long chapterId;

}
