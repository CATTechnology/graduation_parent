package com.graduation.education.user.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graduation.education.user.feign.qo.UserLogLoginQO;
import com.graduation.education.user.feign.vo.UserLogLoginVO;
import com.graduation.education.user.service.dao.UserLogLoginDao;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.graduation.education.user.service.dao.impl.mapper.entity.UserLogLoginExample;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
@Component
public class FeignUserLogLoginBiz {

	@Autowired
	private UserLogLoginDao dao;

	public Page<UserLogLoginVO> listForPage(UserLogLoginQO qo) {
	    UserLogLoginExample example = new UserLogLoginExample();
	    example.setOrderByClause(" id desc ");
        Page<UserLogLogin> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserLogLoginVO.class);
	}

	public int save(UserLogLoginQO qo) {
	    UserLogLogin record = BeanUtil.copyProperties(qo, UserLogLogin.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public UserLogLoginVO getById(Long id) {
	    UserLogLogin record = dao.getById(id);
		return BeanUtil.copyProperties(record, UserLogLoginVO.class);
	}

	public int updateById(UserLogLoginQO qo) {
	    UserLogLogin record = BeanUtil.copyProperties(qo, UserLogLogin.class);
		return dao.updateById(record);
	}

}
