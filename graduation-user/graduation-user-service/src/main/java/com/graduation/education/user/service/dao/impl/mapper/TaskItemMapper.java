package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.TaskItem;
import com.graduation.education.user.service.dao.impl.mapper.entity.TaskItemExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TaskItemMapper继承基类
 */
@Mapper
@Repository
public interface TaskItemMapper extends MyBatisBaseDao<TaskItem, Long, TaskItemExample> {

    /**
     * 查询任务内容
     * @param id
     * @return
     */
    String selectById(Long id);
}