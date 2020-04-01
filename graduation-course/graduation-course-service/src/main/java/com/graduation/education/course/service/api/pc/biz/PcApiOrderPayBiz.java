package com.graduation.education.course.service.api.pc.biz;

import com.graduation.education.course.common.req.OrderPayPageREQ;
import com.graduation.education.course.common.resq.OrderPayPageRESQ;
import com.graduation.education.course.service.dao.OrderPayDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付记录
 */
@Component
public class PcApiOrderPayBiz {

	@Autowired
	private OrderPayDao dao;

	public Result<Page<OrderPayPageRESQ>> list(OrderPayPageREQ req) {
		OrderPayExample example = new OrderPayExample();
		Criteria c = example.createCriteria();
		if (req.getOrderNo() != null) {
			c.andOrderNoEqualTo(req.getOrderNo());
		}
		example.setOrderByClause(" id desc ");
		Page<OrderPay> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, OrderPayPageRESQ.class));
	}

}
