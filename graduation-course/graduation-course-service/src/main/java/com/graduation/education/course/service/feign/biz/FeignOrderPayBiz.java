package com.graduation.education.course.service.feign.biz;

import com.graduation.education.course.feign.qo.OrderPayQO;
import com.graduation.education.course.feign.vo.OrderPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graduation.education.course.service.dao.OrderPayDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Component
public class FeignOrderPayBiz {

    @Autowired
    private OrderPayDao dao;

    public Page<OrderPayVO> listForPage(OrderPayQO qo) {
        OrderPayExample example = new OrderPayExample();
        Criteria c = example.createCriteria();
        if (qo.getOrderNo() != null) {
            c.andOrderNoEqualTo(qo.getOrderNo());
        }
        example.setOrderByClause(" id desc ");
        Page<OrderPay> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, OrderPayVO.class);
    }

    public int save(OrderPayQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public OrderPayVO getById(Long id) {
        OrderPay record = dao.getById(id);
        return BeanUtil.copyProperties(record, OrderPayVO.class);
    }

    public int updateById(OrderPayQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.updateById(record);
    }

}
