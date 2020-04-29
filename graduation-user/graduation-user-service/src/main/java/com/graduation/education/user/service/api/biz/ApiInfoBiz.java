package com.graduation.education.user.service.api.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.AdviceREQ;
import com.graduation.education.user.service.dao.impl.mapper.AdviceMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.user.service.dao.impl.mapper.entity.AdviceExample;
import com.graduation.education.util.base.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:22
 */
@Component
public class ApiInfoBiz {

    @Resource
    private AdviceMapper adviceMapper;

    public PageInfo<Advice> getAllByPage(AdviceREQ adviceREQ) {
        List<Advice> adviceList = (List<Advice>) execute((example) -> {
            AdviceExample.Criteria criteria = example.createCriteria();
            String classNo = adviceREQ.getClassNo();
            if (StringUtils.isNotBlank(classNo)) {
                criteria.andClassNoEqualTo(classNo);
            }

            String teacherName = adviceREQ.getTeacherName();
            if (StringUtils.isNotBlank(teacherName)) {
                criteria.andTeachernameLike("%" + teacherName + "%");
            }

            String title = adviceREQ.getTitle();
            if (StringUtils.isNotBlank(title)) {
                criteria.andTitleLike("%" + title + "%");
            }

            example.setOrderByClause("modify_tine desc");
            PageHelper.startPage(adviceREQ.getPage(), adviceREQ.getSize());
            return adviceMapper.selectByExampleWithBLOBs(example);
        });

        return new PageInfo<>(adviceList);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(String[] code) {
        int i = (int) execute((example) -> {
            AdviceExample adviceExample = new AdviceExample();
            AdviceExample.Criteria criteria = adviceExample.createCriteria();
            criteria.andCodeIn(Arrays.asList(code));
            return adviceMapper.deleteByExample(adviceExample);
        });
        return i > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    public Result<String> add(Advice advice) {
        advice.setCode(UUID.randomUUID().toString().replace("-", ""));
        //暂时设置为1
        advice.setTeacherId(1L);
        //初始化查看次数为0
        advice.setLookTimes(0L);
        Date date = new Date();
        advice.setCreateTime(date);
        advice.setModifyTine(date);
        return adviceMapper.insertSelective(advice) > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    public Result<String> update(Advice advice) {
        int i = (int) execute((example) -> {
            AdviceExample.Criteria criteria = example.createCriteria();
            criteria.andCodeEqualTo(advice.getCode());
            return adviceMapper.updateByExampleSelective(advice, example);
        });
        return i > 0 ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 模板方法
     *
     * @return
     */
    private Object execute(AdviceExampleCriteria adviceExampleCriteria) {
        AdviceExample adviceExample = new AdviceExample();
        return adviceExampleCriteria.execute(adviceExample);
    }

    /**
     * 根据code查询通知
     *
     * @param code
     * @return
     */
    public Advice findOneByCode(String code) {
        List<Advice> adviceList = (List<Advice>) execute((example) -> {
            AdviceExample.Criteria criteria = example.createCriteria();
            criteria.andCodeEqualTo(code);
            return adviceMapper.selectByExampleWithBLOBs(example);
        });
        return CollectionUtils.isNotEmpty(adviceList) ? adviceList.get(0) : null;
    }

    private interface AdviceExampleCriteria {
        Object execute(AdviceExample criteria);
    }

}
