package com.graduation.education.system.service.feign;

import com.graduation.education.system.feign.interfaces.IFeignMsgUser;
import com.graduation.education.system.feign.qo.MsgUserQO;
import com.graduation.education.system.feign.vo.MsgUserVO;
import com.graduation.education.system.service.feign.biz.FeignMsgUserBiz;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@RestController
public class FeignMsgUserController extends BaseController implements IFeignMsgUser {

	@Autowired
	private FeignMsgUserBiz biz;

	@Override
	public Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody MsgUserQO qo){
		return biz.save(qo);
	}

    @Override
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}

    @Override
	public int updateById(@RequestBody MsgUserQO qo){
		return biz.updateById(qo);
	}

    @Override
	public MsgUserVO getById(@RequestBody Long id){
		return biz.getById(id);
	}

}
