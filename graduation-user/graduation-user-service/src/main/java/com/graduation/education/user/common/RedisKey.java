package com.graduation.education.user.common;

import com.alibaba.druid.util.StringUtils;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/28 16:30
 */
public class RedisKey {

    public static final String APPLICATION_NAME = "graduation_user_service";

    private static final String SPLITOR = ":";

    public static String buildKey(String service, String name) {
        return buildKey(service, name, null);
    }

    public static String buildKey(String service, String name, String splitor) {
        if (StringUtils.isEmpty(splitor)) {
            return APPLICATION_NAME + SPLITOR + service + SPLITOR + name;
        }
        return APPLICATION_NAME + splitor + service + splitor + name;
    }
}
