package com.graduation.education.system.feign.interfaces;

import com.graduation.education.system.feign.qo.WebsiteLinkQO;
import com.graduation.education.system.feign.vo.WebsiteLinkVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignWebsiteLink {


    @RequestMapping(value = "/feign/system/websiteLink/listForPage", method = RequestMethod.POST)
    Page<WebsiteLinkVO> listForPage(@RequestBody WebsiteLinkQO qo);

    @RequestMapping(value = "/feign/system/websiteLink/save", method = RequestMethod.POST)
    int save(@RequestBody WebsiteLinkQO qo);

    @RequestMapping(value = "/feign/system/websiteLink/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/websiteLink/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody WebsiteLinkQO qo);

    @RequestMapping(value = "/feign/system/websiteLink/get/{id}", method = RequestMethod.GET)
    WebsiteLinkVO getById(@PathVariable(value = "id") Long id);

}
