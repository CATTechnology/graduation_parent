package com.graduation.education.user.common.resq;

import com.graduation.education.user.service.dao.impl.mapper.entity.Task;
import com.graduation.education.user.service.dao.impl.mapper.entity.TaskItem;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/7 17:52
 */
@Data
@Builder
public class TaskRESQ implements Serializable {

    /**
     * 一级目录
     */
    private List<String> firstTask;

    /**
     * 一级目录对应的二级目录
     */
    private Map<String,List<String>> secondTaskMap;

    /**
     * 查询到的子项目
     */
    private List<TaskItem> taskItemList;
}
