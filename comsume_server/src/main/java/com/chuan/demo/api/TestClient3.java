package com.chuan.demo.api;

import com.chuan.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//fallbackFactory 指定一个fallback工厂,与指定fallback不同, 此工厂可以用来获取到触发断路器的异常信息,TestClientFallbackFactory需要实现FallbackFactory类
@FeignClient(value="mima-cloud-producer",configuration= FeignClientsConfiguration.class,fallbackFactory=TestClien3tFallbackFactory.class)
public interface TestClient3 {
    
    @RequestMapping(value="/get/{id}",method= RequestMethod.GET)
    public String get(@PathVariable("id") String id);
    
    @RequestMapping(value = "/getuser/{id}")
    public User getUser(@PathVariable("id") String id);
    
}