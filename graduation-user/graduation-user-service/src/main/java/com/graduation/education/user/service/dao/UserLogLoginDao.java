package com.graduation.education.user.service.dao;

import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogLoginExample;
import com.graduation.education.util.base.Page;

public interface UserLogLoginDao {
    int save(UserLogLogin record);

    int deleteById(Long id);

    int updateById(UserLogLogin record);

    UserLogLogin getById(Long id);

    Page<UserLogLogin> listForPage(int pageCurrent, int pageSize, UserLogLoginExample example);
}