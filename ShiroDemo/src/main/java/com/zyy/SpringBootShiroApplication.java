package com.zyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 10:27 2018/6/7
 */
@SpringBootApplication
@ServletComponentScan
public class SpringBootShiroApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootShiroApplication.class, args);
    }

}