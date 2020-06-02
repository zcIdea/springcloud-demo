package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName StreamServerApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/1 19:12
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class StreamServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamServerApplication.class,args);
    }
}
