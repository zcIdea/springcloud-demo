package com.chuan.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NacosController
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/28 16:54
 **/
@Slf4j
@RestController
@RefreshScope //主要用来让这个类下的配置内容支持动态刷新，也就是当我们的应用启动之后，修改了Nacos中的配置内容之后，这里也会马上生效
public class NacosServerController {

    @Value("${didispace.title:}")
    private String title;

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        log.info("NacosController hello->name:{}",name);
        return "hello "+name+","+title;
    }



}
