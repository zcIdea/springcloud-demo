package com.chuan.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class DemoListener {

    private static final Logger log= LoggerFactory.getLogger(DemoListener.class);

    //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
    @KafkaListener(id = "demo", topics = "topic.quick.demo")
    public void listen(String msgData) {
        log.info("接收kafka信息，topic:{},msg:{}","topic.quick.demo",msgData);
        log.info("kafka消息接收方 demo receive : "+msgData);
    }

    @KafkaListener(id = "default", topics = "topic.quick.default")
    public void listenDefaultTopic(String msgData) {
        log.info("接收自定义主题kafka信息，topic:{},msg:{}","topic.quick.default",msgData);
        log.info("自定义主题kafka消息接收方 demo receive : "+msgData);
    }
}
