package com.graduation.education.system.service.api;

import com.graduation.education.util.base.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/1 15:45
 */
@RestController
@RequestMapping("/system/api")
public class ApiAdviceController {

    @RequestMapping(value = "/advices", method = RequestMethod.GET)
    public Result<?> list() {

        return Result.success(null);
    }
}
