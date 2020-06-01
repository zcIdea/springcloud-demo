package com.chuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName NacosApplication
 * @Description: 启动类
 * @Author: 张川
 * @Date 2020/5/28 16:52
 **/

@SpringBootApplication
@EnableDiscoveryClient  //启用nacos服务发现与注册功能
@EnableFeignClients     //启用feign远程服务调用
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }

    @Bean
    @LoadBalanced //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    /**
     * WebClient是Spring 5中最新引入的，可以将其理解为reactive版的RestTemplate。
     * 下面举个具体的例子，它将实现与上面RestTemplate一样的请求调用：
     */
    /*@Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }*/


}
