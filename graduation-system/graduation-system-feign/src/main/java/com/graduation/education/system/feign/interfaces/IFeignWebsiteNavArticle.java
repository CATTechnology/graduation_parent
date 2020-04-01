package com.graduation.education.system.feign.interfaces;

import com.graduation.education.system.feign.qo.WebsiteNavArticleQO;
import com.graduation.education.system.feign.vo.WebsiteNavArticleVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 站点导航文章
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignWebsiteNavArticle {


    @RequestMapping(value = "/feign/system/websiteNavArticle/listForPage", method = RequestMethod.POST)
    Page<WebsiteNavArticleVO> listForPage(@RequestBody WebsiteNavArticleQO qo);

    @RequestMapping(value = "/feign/system/websiteNavArticle/save", method = RequestMethod.POST)
    int save(@RequestBody WebsiteNavArticleQO qo);

    @RequestMapping(value = "/feign/system/websiteNavArticle/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/websiteNavArticle/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody WebsiteNavArticleQO qo);

    @RequestMapping(value = "/feign/system/websiteNavArticle/getById", method = RequestMethod.GET)
    WebsiteNavArticleVO getById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/websiteNavArticle/getByNavId/{navId}", method = RequestMethod.GET)
    WebsiteNavArticleVO getByNavId(@PathVariable(value = "navId") Long navId);

}
