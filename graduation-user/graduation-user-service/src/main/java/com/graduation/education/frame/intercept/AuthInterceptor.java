package com.graduation.education.frame.intercept;

import com.graduation.education.frame.intercept.interfaces.Auth;
import com.graduation.education.user.common.RedisKey;
import com.graduation.education.util.enums.ResultEnum;
import com.graduation.education.util.tools.IPUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 15:42
 * 进行权限认证
 */
@Component
@Aspect
public class AuthInterceptor {

    private static final String SCRIPT_LIMIT_IP = "local request_num=redis.call(\"incr\" , KEYS[1]) if tonumber(request_num) == 1 then redis.call(\"expire\",KEYS[1],ARGV[1])return 1 elseif tonumber(request_num)>tonumber(ARGV[2]) then return 0 else return 1 end";

    private HttpServletRequest request;

    private RedisTemplate redis;

    public AuthInterceptor(HttpServletRequest httpServletRequest, StringRedisTemplate redis) {
        this.request = httpServletRequest;
        this.redis = redis;
    }

    /**
     * 定义的一个pointCut
     */
    @Pointcut(value = "execution(* com.graduation.education.user.service.api.*Controller.*(..))")
    private void pointCut() {
    }

    @SneakyThrows
    @Around(value = "pointCut()")
    public Object interceptor(ProceedingJoinPoint jp) {
        String authorization = request.getHeader("Authorization");
        //获取目标对象的类
        Class<?> targetClass = jp.getTarget().getClass();
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method requestMethod = methodSignature.getMethod();
        Auth auth = requestMethod.getAnnotation(Auth.class);

        if (auth != null && auth.required()) {
            if (StringUtils.isBlank(authorization)) {
                //不执行后序代码
                return ResultEnum.TOKEN_MISSING.build();
            }
            //鉴权处理
            String key = RedisKey.buildKey("login", authorization);
            BoundValueOperations<String, String> stringOps = redis.boundValueOps(key);
            if (StringUtils.isBlank(stringOps.get())) {
                //token过期
                return ResultEnum.TOKEN_PAST.build();
            }
        }
        //放行
        return jp.proceed();
    }

    /**
     * ip限流
     * @return
     */
    public boolean isLimit() {
        String ipAddress = IPUtil.getIpAddress(request);
        DefaultRedisScript script = new DefaultRedisScript(SCRIPT_LIMIT_IP , Integer.class);
        String key = RedisKey.buildKey("request_ip:", ipAddress);
        Object execute = redis.execute(script, Arrays.asList(key), "30", "10");
        Integer result = (Integer) execute;
        return result != null && result == 0;
    }
}
