package com.chuan.demo.service;

/**
 * @ClassName EchoServiceImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/12 14:27
 **/
//dubbo的service注解
@org.apache.dubbo.config.annotation.Service
public class EchoServiceImpl implements EchosService {
    @Override
    public String echo(String message) {
        return "[echo] Hello, " + message;
    }
}
