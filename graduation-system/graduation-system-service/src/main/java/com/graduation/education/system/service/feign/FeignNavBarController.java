package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignNavBar;
import com.graduation.education.system.feign.qo.NavBarQO;
import com.graduation.education.system.feign.vo.NavBarVO;
import com.graduation.education.system.service.feign.biz.FeignNavBarBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 头部导航
 *
 * @author wuyun
 */
@RestController
public class FeignNavBarController extends BaseController implements IFeignNavBar {

    @Autowired
    private FeignNavBarBiz biz;

    @Override
    public Page<NavBarVO> listForPage(@RequestBody NavBarQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody NavBarQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody NavBarQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public NavBarVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
