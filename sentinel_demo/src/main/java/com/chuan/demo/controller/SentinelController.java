package com.chuan.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SentinelController
 * @Description: 测试 sentinel 使用
 * @Author: 张川
 * @Date 2020/5/25 17:10
 **/

@Slf4j
@RestController
public class SentinelController {


    @GetMapping("/hello")
    public String hello(){
        return "sentinel";
    }
}
