package com.chuan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableCaching  //启用Cache缓存
//@EnableTransactionManagement //加不加都没啥问题，只要@Transactional注解 就可以添加事物了
public class ProductApplocation {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplocation.class,args);
    }
}
