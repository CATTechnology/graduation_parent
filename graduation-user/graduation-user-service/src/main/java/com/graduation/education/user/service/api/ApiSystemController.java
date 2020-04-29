package com.graduation.education.user.service.api;

import com.graduation.education.user.feign.vo.SysVO;
import com.graduation.education.user.service.api.biz.ApiSystemBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/19 15:03
 */
@RestController
@RequestMapping("/user/api/sys")
public class ApiSystemController {

    @Autowired
    private ApiSystemBiz apiSystemBiz;

    @GetMapping("")
    public SysVO getSys(){
        return apiSystemBiz.getSys();
    }
}
