package com.graduation.education.course.service.dao;

import com.graduation.education.course.service.dao.impl.mapper.entity.Dic;
import com.graduation.education.course.service.dao.impl.mapper.entity.DicExample;
import com.graduation.education.util.base.Page;

public interface DicDao {
    int save(Dic record);

    int deleteById(Long id);

    int updateById(Dic record);

    Dic getById(Long id);

    Page<Dic> listForPage(int pageCurrent, int pageSize, DicExample example);
}