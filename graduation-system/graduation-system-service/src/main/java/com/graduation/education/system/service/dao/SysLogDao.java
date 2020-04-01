package com.graduation.education.system.service.dao;

import com.graduation.education.system.service.dao.impl.mapper.entity.SysLog;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysLogExample;
import com.graduation.education.util.base.Page;

public interface SysLogDao {
    int save(SysLog record);

    int deleteById(Long id);

    int updateById(SysLog record);

    int updateByExampleSelective(SysLog record, SysLogExample example);

    SysLog getById(Long id);

    Page<SysLog> listForPage(int pageCurrent, int pageSize, SysLogExample example);
}