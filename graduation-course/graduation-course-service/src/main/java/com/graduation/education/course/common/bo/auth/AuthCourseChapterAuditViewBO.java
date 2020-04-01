/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.course.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师章节审核信息删除
 *
 * @author WY
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditViewBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID", required = true)
	private Long id;
}
