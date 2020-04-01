package com.graduation.education.course.service.feign;

import java.util.List;

import com.graduation.education.course.feign.interfaces.IFeignOrderInfo;
import com.graduation.education.course.feign.qo.OrderEchartsQO;
import com.graduation.education.course.feign.qo.OrderInfoQO;
import com.graduation.education.course.feign.vo.CountIncomeVO;
import com.graduation.education.course.feign.vo.OrderEchartsVO;
import com.graduation.education.course.feign.vo.OrderInfoVO;
import com.graduation.education.course.feign.vo.OrderReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.education.course.service.feign.biz.FeignOrderInfoBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;

/**
 * 订单信息表
 *
 * @author wujing
 */
@RestController
public class FeignOrderInfoController extends BaseController implements IFeignOrderInfo {

    @Autowired
    private FeignOrderInfoBiz biz;

    @Override
    public Page<OrderInfoVO> listForPage(@RequestBody OrderInfoQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody OrderInfoQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody OrderInfoQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public OrderInfoVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public List<OrderReportVO> listForReport(@RequestBody OrderInfoQO orderInfoQO) {
        return biz.listForReport(orderInfoQO);
    }

    @Override
    public List<OrderEchartsVO> sumByCountOrders(@RequestBody OrderEchartsQO orderEchartsQO) {
        return biz.sumByCountOrders(orderEchartsQO);
    }

    @Override
    public List<OrderEchartsVO> sumByPayTime(@RequestBody OrderEchartsQO orderEchartsQO) {
        return biz.sumByPayTime(orderEchartsQO);
    }

    @Override
    public int handleScheduledTasks() {
        return biz.handleScheduledTasks();
    }

    @Override
    public CountIncomeVO countIncome(@RequestBody OrderInfoQO qo) {
        return biz.countIncome(qo);
    }

}
