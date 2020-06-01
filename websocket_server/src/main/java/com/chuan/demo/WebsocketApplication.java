package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName WebsocketApplication
 * @Description: TODO
 * @Author: zc
 * @Date 2020/5/27 20:55
 **/
@SpringBootApplication(scanBasePackages = "com.chuan.demo")
@EnableFeignClients  //启用Feign 服务远程服务调用，负载均衡
@EnableEurekaClient  //启用eureka服务注册中心
@EnableSwagger2
public class WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class,args);
    }
}
