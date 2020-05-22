package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ConsumeApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/4/23 18:01
 **/
@SpringBootApplication(scanBasePackages = "com.chuan.demo")
@EnableSwagger2
@EnableHystrix       //开启Hystrix支持
@EnableFeignClients  //开启feign支持
public class ConsumeApplication {

    /**
     * 远程连接工具 ribbon
     * @return
     */
    @Bean
    @LoadBalanced //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplication.class,args);
    }
}
