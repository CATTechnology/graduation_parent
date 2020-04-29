package com.graduation.education.user.common.req;

import lombok.Data;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:44
 */
@Data
public class AdviceREQ extends Page implements Serializable {

    @Nullable
    private String classNo;

    @Nullable
    private String teacherName;

    @Nullable
    private String title;
}
