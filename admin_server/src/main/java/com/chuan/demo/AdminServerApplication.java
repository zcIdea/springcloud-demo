package com.chuan.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName com.chuan.demo.AdminServerApplication
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/9 15:12
 **/
@SpringBootApplication
//启用Admin服务器
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class,args);
    }
}
