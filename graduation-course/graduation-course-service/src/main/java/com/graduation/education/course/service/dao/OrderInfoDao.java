package com.graduation.education.course.service.dao;

import java.math.BigDecimal;
import java.util.List;

import com.graduation.education.course.feign.qo.OrderInfoQO;
import com.graduation.education.course.feign.vo.OrderReportVO;
import com.graduation.education.course.common.resq.CountIncomeRESQ;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.graduation.education.course.service.dao.impl.mapper.entity.OrderInfoExample;
import com.graduation.education.util.base.Page;

public interface OrderInfoDao {
    int save(OrderInfo record);

    int deleteById(Long id);

    int updateById(OrderInfo record);

    OrderInfo getById(Long id);

    Page<OrderInfo> listForPage(int pageCurrent, int pageSize, OrderInfoExample example);

    OrderInfo getByUserNoAndCourseId(Long userNo, Long courseId);

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderNo
     * @return
     */
    OrderInfo getByOrderNo(long orderNo);

    /**
     * 统计时间段内该讲师的订单收益
     *
     * @param lecturerUserNo
     * @param date
     * @return
     */
    BigDecimal sumLecturerUserNoAndData(Long lecturerUserNo, String date);

    /**
     * 统计时间段内的总订单数
     *
     * @param date
     * @return
     */
    Integer sumByCountOrders(String date);

    /**
     * 统计时间段的总收入
     *
     * @param date
     * @return
     */
    BigDecimal sumByPayTime(String date);

    /**
     * 统计订单收入情况
     *
     * @param qo
     */
    CountIncomeRESQ countIncome(OrderInfoQO qo);

    /**
     * 订单信息汇总（导出报表）
     *
     * @param orderInfoQO
     */
    List<OrderReportVO> listForReport(OrderInfoQO orderInfoQO);
}
