package com.graduation.education.system.service.feign.biz;

import com.graduation.education.system.feign.qo.SysQO;
import com.graduation.education.system.feign.vo.SysVO;
import com.graduation.education.system.service.dao.SysDao;
import com.graduation.education.system.service.dao.impl.mapper.entity.Sys;
import com.graduation.education.system.service.dao.impl.mapper.entity.SysExample;
import com.graduation.education.util.base.BaseException;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.enums.FileTypeEnum;
import com.graduation.education.util.enums.SysTypeEnum;
import com.graduation.education.util.enums.VideoTypeEnum;
import com.graduation.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@Component
public class FeignSysBiz {

	@Autowired
	private SysDao dao;

	public Page<SysVO> listForPage(SysQO qo) {
		SysExample example = new SysExample();
		example.setOrderByClause(" id desc ");
		Page<Sys> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysVO.class);
	}

	public int save(SysQO qo) {
		Sys record = BeanUtil.copyProperties(qo, Sys.class);
		if (VideoTypeEnum.QINIU.getCode().equals(record.getVideoType())) {
			throw new BaseException("视频存储暂未支持七牛");
		}
		if (FileTypeEnum.QINIU.getCode().equals(record.getFileType())) {
			throw new BaseException("文件存储暂未支持七牛");
		}
		if (SysTypeEnum.OTHERPAYMENT.getCode().equals(record.getPayType())) {
			throw new BaseException("支付通道暂时只支持龙果支付");
		}
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysVO getById(Long id) {
		Sys record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysVO.class);
	}

	public int updateById(SysQO qo) {
		Sys record = BeanUtil.copyProperties(qo, Sys.class);
		if (VideoTypeEnum.QINIU.getCode().equals(record.getVideoType())) {
			throw new BaseException("视频存储暂未支持七牛");
		}
		if (FileTypeEnum.QINIU.getCode().equals(record.getFileType())) {
			throw new BaseException("文件存储暂未支持七牛");
		}
		if (SysTypeEnum.OTHERPAYMENT.getCode().equals(record.getPayType())) {
			throw new BaseException("支付通道暂时只支持龙果支付");
		}
		return dao.updateById(record);
	}

	public SysVO getSys() {
		return BeanUtil.copyProperties(dao.getSys(), SysVO.class);
	}

}
