package com.graduation.education.user.service.course;

import com.graduation.education.util.tools.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/26 20:03
 */
public class ApiTest {

    private final String hosts = "http://120.55.151.61/";

    private final String login_url = hosts + "V2/StudentSkip/loginCheckV4.action";

    @Test
    public void loginTest() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("platform", 1);
        paramMap.put("phoneVersion", 19);
        paramMap.put("deviceCode", "860087045424054860087045424050");
        paramMap.put("account", SuperClassUtil.encrypt("15886449241"));
        paramMap.put("versionNumber", "9.4.0");
        paramMap.put("password", SuperClassUtil.encrypt("dailingfei520"));
        paramMap.put("channel", "ppMarket");
        paramMap.put("updateInfo", false);
        paramMap.put("phoneBrand", "HUAWEI");
        paramMap.put("phoneModel", "PCT-AL10");
        String resp = HttpUtil.postJSON(login_url, paramMap);
        System.out.println(resp);
    }

    @Test
    public void userManagerTest() {
        String url = "https://open.seiue.com/api/v1/reflections";
        //
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1);
        paramMap.put("name", 19);
        paramMap.put("gender", 'm');
        paramMap.put("avatar_url", "");
        paramMap.put("usin", 1780987);
        paramMap.put("role", 1);
        paramMap.put("email", "2221734739@qq.com");
//        paramMap.put("updateInfo", false);
//        paramMap.put("phoneBrand", "HUAWEI");
//        paramMap.put("phoneModel", "PCT-AL10");
        String json = HttpUtil.postJSON(url, paramMap);
        System.out.println(json);
    }
}
