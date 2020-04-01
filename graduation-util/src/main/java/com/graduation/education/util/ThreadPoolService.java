package com.graduation.education.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/28 16:20
 */
@Component
public class ThreadPoolService {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(25);

    /**
     * 异步执行给定的任务
     * @param task
     */
    public void asyncExec(Runnable task){
        executorService.execute(task);
    }

}
