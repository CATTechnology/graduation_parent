package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.Task;
import com.graduation.education.user.service.dao.impl.mapper.entity.TaskExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TaskMapper继承基类
 */
@Mapper
@Repository
public interface TaskMapper extends MyBatisBaseDao<Task, Long, TaskExample> {
}