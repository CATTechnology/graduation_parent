package com.graduation.education.system.service.api.biz;

import com.graduation.education.system.service.dao.impl.mapper.AdviceMapper;
import com.graduation.education.system.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.system.service.dao.impl.mapper.entity.AdviceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通知信息
 *
 * @author YZJ
 */
@Component
public class ApiAdviceBiz {

    @Autowired
    private AdviceMapper adviceMapper;

    public List<Advice> list(){
        return adviceMapper.selectByExample(null);
    }
}
