package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignWebsiteNav;
import com.graduation.education.system.feign.qo.WebsiteNavQO;
import com.graduation.education.system.feign.vo.WebsiteNavVO;
import com.graduation.education.system.service.feign.biz.FeignWebsiteNavBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航
 *
 * @author wuyun
 */
@RestController
public class FeignWebsiteNavController extends BaseController implements IFeignWebsiteNav {

	@Autowired
	private FeignWebsiteNavBiz biz;

	@Override
	public Page<WebsiteNavVO> listForPage(@RequestBody WebsiteNavQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteNavQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteNavQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteNavVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

}
