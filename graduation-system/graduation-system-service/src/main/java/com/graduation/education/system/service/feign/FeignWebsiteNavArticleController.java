package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignWebsiteNavArticle;
import com.graduation.education.system.feign.qo.WebsiteNavArticleQO;
import com.graduation.education.system.feign.vo.WebsiteNavArticleVO;
import com.graduation.education.system.service.feign.biz.FeignWebsiteNavArticleBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航文章
 *
 */
@RestController
public class FeignWebsiteNavArticleController extends BaseController implements IFeignWebsiteNavArticle {

	@Autowired
	private FeignWebsiteNavArticleBiz biz;

	@Override
	public Page<WebsiteNavArticleVO> listForPage(@RequestBody WebsiteNavArticleQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteNavArticleQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteNavArticleQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteNavArticleVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}

	@Override
	public WebsiteNavArticleVO getByNavId(@PathVariable(value = "navId") Long navId) {
		return biz.getByNavId(navId);
	}

}
