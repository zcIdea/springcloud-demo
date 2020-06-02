package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName StreamClientApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/1 19:30
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class StreamClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamClientApplication.class,args);
    }

}
