package com.graduation.education.user.common.resq;

import com.graduation.education.user.service.dao.impl.mapper.entity.Task;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/17 18:02
 */
@Data
@Builder
public class CategoryRESQ implements Serializable {

    /**
     * 一级列表
     */
    private List<String> firstTask;

    /**
     * 联动二级列表
     */
    private Map<String , List<Task>> secondTask;

}
