/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.user.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class UserRegisterBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 学生或者老师或者普通用户
	 * 1 学生 ， 2 老师， 3 普通用户
	 */
	private int type;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机", required = true)
	protected String mobile;

	/**
	 * 登录密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	protected String password;
	/**
	 * 重复密码
	 */
	@ApiModelProperty(value = "重复密码", required = true)
	protected String repassword;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
	protected String clientId;
	/**
	 * 手机验证码
	 */
//	@ApiModelProperty(value = "手机验证码", required = true)
//	protected String code;

	protected String ip;

}
