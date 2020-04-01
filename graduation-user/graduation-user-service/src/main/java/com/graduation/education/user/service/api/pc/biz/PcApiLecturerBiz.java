package com.graduation.education.user.service.api.pc.biz;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.graduation.education.user.common.req.LecturerPageREQ;
import com.graduation.education.user.common.req.LecturerUpdateREQ;
import com.graduation.education.user.common.req.LecturerViewREQ;
import com.graduation.education.user.common.resq.LecturerExtViewRESQ;
import com.graduation.education.user.common.resq.LecturerPageRESQ;
import com.graduation.education.user.common.resq.LecturerViewRESQ;
import com.graduation.education.user.service.dao.LecturerAuditDao;
import com.graduation.education.user.service.dao.LecturerDao;
import com.graduation.education.user.service.dao.LecturerExtDao;
import com.graduation.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerAudit;
import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerExample;
import com.graduation.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.graduation.education.util.base.BaseException;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.enums.StatusIdEnum;
import com.graduation.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Component
public class PcApiLecturerBiz {

	@Autowired
	private LecturerDao lecturerDao;
	@Autowired
	private LecturerAuditDao lecturerAuditDao;
	@Autowired
	private LecturerExtDao lecturerExtDao;

	public Result<Page<LecturerPageRESQ>> listForPage(LecturerPageREQ req) {
		LecturerExample example = new LecturerExample();
		LecturerExample.Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getLecturerMobile())) {
			c.andLecturerMobileLike(PageUtil.like(req.getLecturerMobile()));
		}
		if (StringUtils.hasText(req.getLecturerName())) {
			c.andLecturerNameLike(PageUtil.rightLike(req.getLecturerName()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Lecturer> page = lecturerDao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<LecturerPageRESQ> listPage = PageUtil.transform(page, LecturerPageRESQ.class);
		for (LecturerPageRESQ resq : listPage.getList()) {
			resq.setLecturerProportion(resq.getLecturerProportion().multiply(BigDecimal.valueOf(100)));
		}
		return Result.success(listPage);
	}

	/**
	 * 讲师信息查看接口
	 *
	 * @param req
	 */
	public Result<LecturerViewRESQ> view(LecturerViewREQ req) {
		Lecturer record = new Lecturer();
		if (req.getId() != null) {
			record = lecturerDao.getById(req.getId());
		}
		if (req.getLecturerUserNo() != null) {
			record = lecturerDao.getByLecturerUserNoAndStatusId(req.getLecturerUserNo(), StatusIdEnum.YES.getCode());
		}
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到该讲师信息");
		}
		LecturerViewRESQ vo = BeanUtil.copyProperties(record, LecturerViewRESQ.class);
		if (vo.getLecturerProportion() != null) {
			vo.setLecturerProportion(vo.getLecturerProportion().multiply(BigDecimal.valueOf(100)));
		}
		// 讲师账户信息
		LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(vo.getLecturerUserNo());
		vo.setLecturerExt(BeanUtil.copyProperties(lecturerExt, LecturerExtViewRESQ.class));
		return Result.success(vo);
	}

	/**
	 * 更新讲师信息直接生效
	 *
	 * @param req
	 * @return
	 */
	@Transactional
	public Result<Integer> update(LecturerUpdateREQ req) {
		if (null == req.getId()) {
			return Result.error("讲师编号不能为空");
		}
		Lecturer lecturer = lecturerDao.getById(req.getId());
		if (ObjectUtil.isNull(lecturer)) {
			return Result.error("找不到该讲师");
		}
		Lecturer record = BeanUtil.copyProperties(req, Lecturer.class);
		if (req.getLecturerProportion() != null) {
			record.setLecturerProportion(req.getLecturerProportion().divide(BigDecimal.valueOf(100)));
		}
		int lecturerNum = lecturerDao.updateById(record);
		if (lecturerNum < 1) {
			throw new BaseException("讲师信息表更新失败");
		}
		int results = lecturerAuditDao.updateById(BeanUtil.copyProperties(record, LecturerAudit.class));
		if (results < 0) {
			return Result.error(ResultEnum.USER_UPDATE_FAIL);
		}
		return Result.success(results);
	}

}
