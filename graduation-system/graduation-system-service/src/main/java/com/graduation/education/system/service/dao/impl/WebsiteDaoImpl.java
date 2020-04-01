package com.graduation.education.system.service.dao.impl;

import com.graduation.education.system.service.dao.WebsiteDao;
import com.graduation.education.system.service.dao.impl.mapper.WebsiteMapper;
import com.graduation.education.system.service.dao.impl.mapper.entity.Website;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteExample;
import com.graduation.education.system.service.dao.impl.mapper.entity.WebsiteExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebsiteDaoImpl implements WebsiteDao {
	@Autowired
	private WebsiteMapper websiteMapper;

	@Override
	public int save(Website record) {
		record.setId(IdWorker.getId());
		return this.websiteMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.websiteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Website record) {
		return this.websiteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Website getById(Long id) {
		return this.websiteMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Website> listForPage(int pageCurrent, int pageSize, WebsiteExample example) {
		int count = this.websiteMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Website>(count, totalPage, pageCurrent, pageSize, this.websiteMapper.selectByExample(example));
	}

	@Override
	public Website getWebsite() {
		WebsiteExample example = new WebsiteExample();
		List<Website> resultList = this.websiteMapper.selectByExample(example);
		if (resultList == null || resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Website getByStatusId(Integer statusId) {
		WebsiteExample example = new WebsiteExample();
		Criteria C = example.createCriteria();
		C.andStatusIdEqualTo(statusId);
		List<Website> list = this.websiteMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}