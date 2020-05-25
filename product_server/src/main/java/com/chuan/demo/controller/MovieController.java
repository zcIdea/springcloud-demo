package com.chuan.demo.controller;

import com.chuan.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 通过注解@HystrixCommand的commandProperties去配置，
 * 如下就是hystrix命令超时时间命令执行超时时间，为1000ms和执行是不启用超时
 */

@RestController
public class MovieController {

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/movie/{id}")
  @HystrixCommand(
          commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")},
          fallbackMethod = "findByIdFallback")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
  }

  /**
   * fallback方法
   * @param id
   * @return
     */
  public User findByIdFallback(Long id) {
    User user = new User();
    user.setId(5L);
    return user;
  }
}