package com.chuan.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  name/value属性: 这两个的作用是一样的,指定的是调用服务的微服务名称
 *  url : 指定调用服务的全路径,经常用于本地测试
 *  如果同时指定name和url属性: 则以url属性为准,name属性指定的值便当做客户端的名称
 */
//@FeignClient("nacos-server")
@FeignClient(name = "ClientApi",url = "http://127.0.0.1:8765")
public interface ClientApi {

    @GetMapping("/hello")
    String hello(@RequestParam(name = "name") String name);
}
