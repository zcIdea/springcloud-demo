package com.chuan.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SentinelController
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/25 17:10
 **/

@RestController
@Slf4j
public class SentinelController {


    @GetMapping("/hello")
    public String hello(){
        return "sentinel";
    }
}
