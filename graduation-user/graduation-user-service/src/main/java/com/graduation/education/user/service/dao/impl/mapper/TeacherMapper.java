package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.Teacher;
import com.graduation.education.user.service.dao.impl.mapper.entity.TeacherExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TeacherMapper继承基类
 */
@Mapper
@Repository
public interface TeacherMapper extends MyBatisBaseDao<Teacher, Long, TeacherExample> {
}