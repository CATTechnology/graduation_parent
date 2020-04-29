/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.user.common.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TeacherRegisterBO extends UserRegisterBO implements Serializable {

}
