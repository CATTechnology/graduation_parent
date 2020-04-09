package com.graduation.education.system.service.api;

import com.graduation.education.system.service.api.biz.ApiAdviceBiz;
import com.graduation.education.system.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/1 15:45
 */
@RestController
@RequestMapping("/system/api")
public class ApiAdviceController {

    @Autowired
    private ApiAdviceBiz apiAdviceBiz;

    @RequestMapping(value = "/advices", method = RequestMethod.GET)
    public Result<ArrayList<Advice>> list() {
        List<Advice> adviceList = apiAdviceBiz.list();
        return Result.success(new ArrayList<>(adviceList));
    }
}
