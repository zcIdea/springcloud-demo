package com.chuan.demo.controller;

import com.chuan.demo.service.EchosService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DubboClientController
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/12 15:18
 **/
@Slf4j
@RestController
public class DubboClientController {


    /**
     * dubbo的rpc服务调用
     */
    @Reference
    private EchosService echosService;

    @GetMapping("/echo")
    public String echo(String message){
        log.info("DubboClientController echo->message:{}",message);
        log.info("查询消息");
        return echosService.echo(message);
    }
}
