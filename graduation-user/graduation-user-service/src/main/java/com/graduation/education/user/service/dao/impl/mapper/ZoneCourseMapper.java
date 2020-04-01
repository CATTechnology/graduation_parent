package com.graduation.education.user.service.dao.impl.mapper;

import com.graduation.education.user.service.dao.impl.mapper.entity.ZoneCourse;
import com.graduation.education.user.service.dao.impl.mapper.entity.ZoneCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZoneCourseMapper {
    int countByExample(ZoneCourseExample example);

    int deleteByExample(ZoneCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZoneCourse record);

    int insertSelective(ZoneCourse record);

    List<ZoneCourse> selectByExample(ZoneCourseExample example);

    ZoneCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZoneCourse record, @Param("example") ZoneCourseExample example);

    int updateByExample(@Param("record") ZoneCourse record, @Param("example") ZoneCourseExample example);

    int updateByPrimaryKeySelective(ZoneCourse record);

    int updateByPrimaryKey(ZoneCourse record);
}