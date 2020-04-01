package com.graduation.education.user.service.dao.impl;

import com.graduation.education.user.service.dao.CourseVideoDao;
import com.graduation.education.user.service.dao.impl.mapper.CourseVideoMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.CourseVideo;
import com.graduation.education.user.service.dao.impl.mapper.entity.CourseVideoExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseVideoDaoImpl implements CourseVideoDao {
	@Autowired
	private CourseVideoMapper courseVideoMapper;

	@Override
	public int save(CourseVideo record) {
		record.setId(IdWorker.getId());
		return this.courseVideoMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseVideoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseVideo record) {
		return this.courseVideoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseVideo getById(Long id) {
		return this.courseVideoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example) {
		int count = this.courseVideoMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseVideo>(count, totalPage, pageCurrent, pageSize, this.courseVideoMapper.selectByExample(example));
	}

	@Override
	public CourseVideo getByVideoNoAndPeriodId(Long videoNo, Long periodId) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		c.andPeriodIdEqualTo(periodId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByVideoNo(Long videoNo) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public List<CourseVideo> listByPeriodIdAndStatusId(Long periodId, Integer statusId) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andPeriodIdEqualTo(periodId);
		c.andStatusIdEqualTo(statusId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public CourseVideo getByVideoNoAndStatusId(Long videoNo, Integer statusId) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		c.andStatusIdEqualTo(statusId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByVideoNoAndNotEqualPeriodIdAndStatusId(Long videoNo, Long periodId, Integer statusId) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		c.andPeriodIdNotEqualTo(periodId);
		c.andStatusIdEqualTo(statusId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public CourseVideo getByVideoNo(Long videoNo) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByChapterIdAndPeriodIdAndStatusId(Long chapterId, Long periodId, Integer statusId) {
		CourseVideoExample example = new CourseVideoExample();
		CourseVideoExample.Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andPeriodIdEqualTo(periodId);
		c.andStatusIdEqualTo(statusId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}
}