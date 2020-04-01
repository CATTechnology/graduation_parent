package com.graduation.education.user.service.dao.impl;

import com.graduation.education.user.service.dao.UserLogModifiedDao;
import com.graduation.education.user.service.dao.impl.mapper.UserLogModifiedMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogModified;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogModifiedExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogModifiedDaoImpl implements UserLogModifiedDao {
    @Autowired
    private UserLogModifiedMapper userLogModifiedMapper;

    public int save(UserLogModified record) {
        record.setId(IdWorker.getId());
        return this.userLogModifiedMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.userLogModifiedMapper.deleteByPrimaryKey(id);
    }

    public int updateById(UserLogModified record) {
        return this.userLogModifiedMapper.updateByPrimaryKeySelective(record);
    }

    public UserLogModified getById(Long id) {
        return this.userLogModifiedMapper.selectByPrimaryKey(id);
    }

    public Page<UserLogModified> listForPage(int pageCurrent, int pageSize, UserLogModifiedExample example) {
        int count = this.userLogModifiedMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<UserLogModified>(count, totalPage, pageCurrent, pageSize, this.userLogModifiedMapper.selectByExample(example));
    }
}