package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName DubboSpringCloudClientApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/12 15:17
 **/
@EnableDiscoveryClient
@EnableAutoConfiguration
public class DubboSpringCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSpringCloudClientApplication.class,args);
    }

}
