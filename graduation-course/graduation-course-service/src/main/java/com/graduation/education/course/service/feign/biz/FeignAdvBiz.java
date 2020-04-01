package com.graduation.education.course.service.feign.biz;

import com.graduation.education.course.feign.qo.AdvQO;
import com.graduation.education.course.feign.vo.AdvVO;
import com.graduation.education.system.feign.interfaces.IFeignSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.graduation.education.course.service.dao.AdvDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.Adv;
import com.graduation.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.graduation.education.course.service.dao.impl.mapper.entity.AdvExample.Criteria;
import com.graduation.education.util.aliyun.Aliyun;
import com.graduation.education.util.aliyun.AliyunUtil;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.tools.BeanUtil;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
public class FeignAdvBiz {

    @Autowired
    private AdvDao dao;

    @Autowired
    private IFeignSys feignSys;

    public Page<AdvVO> listForPage(AdvQO qo) {
        AdvExample example = new AdvExample();
        Criteria c = example.createCriteria();
        if (qo.getPlatShow() != null) {
            c.andPlatShowEqualTo(qo.getPlatShow());
        }
        if (StringUtils.hasText(qo.getAdvTitle())) {
            c.andAdvTitleEqualTo(qo.getAdvTitle());
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Adv> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, AdvVO.class);
    }

    public int save(AdvQO qo) {
        Adv record = BeanUtil.copyProperties(qo, Adv.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        Adv adv = dao.getById(id);
        if (adv != null) {
            AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(feignSys.getSys(), Aliyun.class));
        }
        return dao.deleteById(id);
    }

    public AdvVO getById(Long id) {
        Adv record = dao.getById(id);
        return BeanUtil.copyProperties(record, AdvVO.class);
    }

    public int updateById(AdvQO qo) {
        Adv adv = dao.getById(qo.getId());
        if (StringUtils.hasText(qo.getAdvImg()) && !adv.getAdvImg().equals(qo.getAdvImg())) {
            AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(feignSys.getSys(), Aliyun.class));
        }
        Adv record = BeanUtil.copyProperties(qo, Adv.class);
        return dao.updateById(record);
    }

}
