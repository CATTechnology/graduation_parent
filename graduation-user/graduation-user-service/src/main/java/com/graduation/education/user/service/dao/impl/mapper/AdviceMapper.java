package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.user.service.dao.impl.mapper.entity.AdviceExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AdviceMapper继承基类
 */
@Mapper
@Repository
public interface AdviceMapper extends MyBatisBaseDao<Advice, Long, AdviceExample> {
}