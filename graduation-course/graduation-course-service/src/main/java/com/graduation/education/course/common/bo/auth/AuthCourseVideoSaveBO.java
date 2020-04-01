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
public class AuthCourseVideoSaveBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号")
	private Long videoNo;
	/**
	 * 章节ID（添加章节视频时传入）
	 */
	@ApiModelProperty(value = "章节ID")
	private Long chapterId;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Long userNo;
}
