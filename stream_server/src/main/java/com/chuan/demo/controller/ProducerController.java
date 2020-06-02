package com.chuan.demo.controller;

import com.chuan.demo.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@Slf4j
@RestController
public class ProducerController {

    @Autowired
    private SendService sendService;

    @Value("${didispace.title:}")
    private String title;

    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg") String msg){
        log.info("ProducerController send--> msg:{}",msg);
        log.info("发送信息到kafka中");
//        sendService.sendMsg(msg+"==============配置title:"+title);
        sendService.sendMyMsg(msg+"==============配置title:"+title);
        log.info("发送成功！");
    }
}