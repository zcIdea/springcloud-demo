package com.chuan.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangchuan
 * @date zhangChuan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulDemoApplication.class,args);
    }
}
