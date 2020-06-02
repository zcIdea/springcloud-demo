package com.chuan.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 *这个注解给我们绑定消息通道的，Source是Stream给我们提供的，可以点进去看源码，
 * 可以看到output和input,这和配置文件中的output，input对应的。
 */

//@EnableBinding(Source.class)
@EnableBinding(MySource.class)
public class SendService {

    //默认的通道
//    @Autowired
//    private Source source;

    //自定义的通道
    @Autowired
    private MySource mySource;

    /*public void sendMsg(String msg){
        source.output().send(MessageBuilder.withPayload(msg).build());
    }*/
    public void sendMyMsg(String msg){
        mySource.myOutput().send(MessageBuilder.withPayload(msg).build());
    }

}