package com.chuan.demo.controller;

import com.chuan.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello() {
//        return "hello";
        return helloService.hello();
    }

    @GetMapping("/hello4")
    public List<String> hello4() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("张三:" + i);
        }
        return list;
    }
}