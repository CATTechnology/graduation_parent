package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.Student;
import com.graduation.education.user.service.dao.impl.mapper.entity.StudentExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * StudentMapper继承基类
 */
@Mapper
@Repository
public interface StudentMapper extends MyBatisBaseDao<Student, Long, StudentExample> {
}