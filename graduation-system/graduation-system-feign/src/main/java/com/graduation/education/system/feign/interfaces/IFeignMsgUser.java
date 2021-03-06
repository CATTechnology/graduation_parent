package com.graduation.education.system.feign.interfaces;

import com.graduation.education.system.feign.qo.MsgUserQO;
import com.graduation.education.system.feign.vo.MsgUserVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignMsgUser {

    @RequestMapping(value = "/feign/system/msgUser/listForPage")
    Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo);

    @RequestMapping(value = "/feign/system/msgUser/save")
    int save(@RequestBody MsgUserQO qo);

    @RequestMapping(value = "/feign/system/msgUser/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/msgUser/updateById")
    int updateById(@RequestBody MsgUserQO qo);

    @RequestMapping(value = "/feign/system/msgUser/getById")
    MsgUserVO getById(@RequestBody Long id);

}
