package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName DubboServerApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/12 14:26
 **/
@EnableDiscoveryClient
//@EnableAutoConfiguration
@SpringBootApplication
public class DubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServerApplication.class,args);
    }

}
