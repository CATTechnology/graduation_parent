package com.graduation.education.user.service.api.biz;

import com.graduation.education.user.feign.vo.SysVO;
import com.graduation.education.user.service.dao.SysDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/19 15:06
 */
@Component
public class ApiSystemBiz {

    @Autowired
    private SysDao sysDao;

    public SysVO getSys() {
        return sysDao.getSys();
    }
}
