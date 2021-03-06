package com.graduation.education.user.feign.interfaces;

import com.graduation.education.user.feign.qo.RegionQO;
import com.graduation.education.user.feign.vo.RegionVO;
import com.graduation.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 行政区域表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignRegion {

    @RequestMapping(value = "/feign/user/region/listForPage", method = RequestMethod.POST)
    Page<RegionVO> listForPage(@RequestBody RegionQO qo);

    @RequestMapping(value = "/feign/user/region/save", method = RequestMethod.POST)
    int save(@RequestBody RegionQO qo);

    @RequestMapping(value = "/feign/user/region/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/region/update", method = RequestMethod.PUT)
    int updateById(@RequestBody RegionQO qo);

    @RequestMapping(value = "/feign/user/region/get/{id}", method = RequestMethod.GET)
    RegionVO getById(@PathVariable(value = "id") Long id);

}
