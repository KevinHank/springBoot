package com.kevin.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Hukai
 * 2018-09-04 0004
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    StringEncryptor stringEncryptor;

    @org.junit.Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("root");
        System.out.println(result);
    }
}
