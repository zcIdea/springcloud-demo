package com.chuan.demo.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 因为Fallback是通过Hystrix实现的，
 * 所以需要开启Hystrix，spring boot application.properties文件配置feign.hystrix.enabled=true，这样就开启了Fallback
 * @FeignClient.fallback = TestClientFallback.class指定一个实现Feign接口的实现类。
 */
@FeignClient(value = "product-server",
        configuration = FeignClientsConfiguration.class,
        fallback = TestClientFallback.class)
public interface TestClient {

    @GetMapping("/getUserList")
    List<String> getUserList(@RequestParam("id") Long id,@RequestParam("name") String name);
}
