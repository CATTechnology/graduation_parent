/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.graduation.education.user.service.test;

import org.junit.Test;

import java.util.UUID;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
public class BaseTest {

    @Test
    public void generateNumber() {
        int count = 19;
        while (count > 0) {
            System.out.print((int)(Math.random() * 10 + 1));
            count--;
        }
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }


}
