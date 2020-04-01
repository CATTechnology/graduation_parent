package com.graduation.education.course.service.api.pc;

import com.graduation.education.course.service.api.pc.biz.PcApiOrderPayBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.education.course.common.req.OrderPayPageREQ;
import com.graduation.education.course.common.resq.OrderPayPageRESQ;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 支付记录
 */
@RestController
@RequestMapping(value = "/course/pc/order/pay")
public class PcApiOrderPayController extends BaseController {

    @Autowired
    private PcApiOrderPayBiz biz;

    /**
     * 分页列出支付记录
     *
     * @return
     */
    @ApiOperation(value = "支付记录分页列表接口", notes = "支付记录分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<OrderPayPageRESQ>> list(@RequestBody OrderPayPageREQ orderPayPageREQ) {
        return biz.list(orderPayPageREQ);
    }

}
