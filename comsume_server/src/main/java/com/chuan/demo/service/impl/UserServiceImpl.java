package com.chuan.demo.service.impl;

import com.chuan.demo.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/15 14:48
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMethod")
    @Override
    public String getUserById(Long id) {
        String user = restTemplate.getForObject("http://localhost:8768/getUser/" + id, String.class);
        return user;
    }

    public String getFallbackMethod(Long id){
        return "hi,"+id+",sorry,error!";
    }
}
