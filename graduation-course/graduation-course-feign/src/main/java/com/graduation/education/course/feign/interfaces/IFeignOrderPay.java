package com.graduation.education.course.feign.interfaces;

import com.graduation.education.course.feign.qo.OrderPayQO;
import com.graduation.education.course.feign.vo.OrderPayVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignOrderPay {

    @RequestMapping(value = "/feign/course/orderPay/listForPage", method = RequestMethod.POST)
    Page<OrderPayVO> listForPage(@RequestBody OrderPayQO qo);

    @RequestMapping(value = "/feign/course/orderPay/save", method = RequestMethod.POST)
    int save(@RequestBody OrderPayQO qo);

    @RequestMapping(value = "/feign/course/orderPay/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/orderPay/update", method = RequestMethod.PUT)
    int updateById(@RequestBody OrderPayQO qo);

    @RequestMapping(value = "/feign/course/orderPay/get/{id}", method = RequestMethod.GET)
    OrderPayVO getById(@PathVariable(value = "id") Long id);

}
