package com.chuan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ProductApplocation
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/4/23 17:50
 **/
@SpringBootApplication(scanBasePackages = "com.chuan.demo")
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.chuan.demo.dao")
@EnableZuulProxy
public class ProductApplocation {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplocation.class,args);
    }
}
