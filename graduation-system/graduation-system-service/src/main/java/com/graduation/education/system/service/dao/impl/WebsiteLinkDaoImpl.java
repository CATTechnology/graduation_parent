package com.graduation.education.system.service.dao.impl;

import com.graduation.education.system.service.dao.WebsiteLinkDao;
import com.graduation.education.system.service.dao.impl.mapper.WebsiteLinkMapper;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebsiteLinkDaoImpl implements WebsiteLinkDao {
    @Autowired
    private WebsiteLinkMapper websiteLinkMapper;

    public int save(WebsiteLink record) {
        record.setId(IdWorker.getId());
        return this.websiteLinkMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.websiteLinkMapper.deleteByPrimaryKey(id);
    }

    public int updateById(WebsiteLink record) {
        return this.websiteLinkMapper.updateByPrimaryKeySelective(record);
    }

    public WebsiteLink getById(Long id) {
        return this.websiteLinkMapper.selectByPrimaryKey(id);
    }

    public Page<WebsiteLink> listForPage(int pageCurrent, int pageSize, WebsiteLinkExample example) {
        int count = this.websiteLinkMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<WebsiteLink>(count, totalPage, pageCurrent, pageSize, this.websiteLinkMapper.selectByExample(example));
    }
    
    @Override
	public List<WebsiteLink> listByStatusId(Integer statusId) {
		WebsiteLinkExample example = new WebsiteLinkExample();
		Criteria c = example.createCriteria();
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause(" sort desc, id desc ");
		return this.websiteLinkMapper.selectByExample(example);
	}
    
}