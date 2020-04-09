package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.StudentSign;
import com.graduation.education.user.service.dao.impl.mapper.entity.StudentSignExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * StudentSignMapper继承基类
 */
@Mapper
@Repository
public interface StudentSignMapper extends MyBatisBaseDao<StudentSign, Long, StudentSignExample> {
}