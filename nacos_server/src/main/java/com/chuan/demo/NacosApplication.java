package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName NacosApplication
 * @Description: TODO
 * @Author: zc
 * @Date 2020/5/28 16:52
 **/

@SpringBootApplication
@EnableDiscoveryClient  //启用nacos服务发现与注册功能
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class,args);
    }

}
