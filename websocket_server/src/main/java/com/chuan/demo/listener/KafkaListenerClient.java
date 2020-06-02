package com.chuan.demo.listener;

import com.chuan.demo.config.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka监听器，实时监听kafka发送的信息，并推送到页面index.html
 */
@Component
public class KafkaListenerClient {

    private static final Logger log= LoggerFactory.getLogger(KafkaListenerClient.class);


    @Autowired
    private WebSocketServer webSocketServer;

    //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
    @KafkaListener(id = "demo", topics = "topic.quick.demo")
    public void listen(String msgData) {
        log.info("接收kafka信息，topic:{},msg:{}","topic.quick.demo",msgData);
        log.info("kafka消息接收方 demo receive : "+msgData);

        log.info("通过webSocket主动推送kafka信息到页面 : "+msgData);
        webSocketServer.sendToUser("11111111111", msgData);
        log.info("webSocket推送信息到页面成功");
    }

    @KafkaListener(id = "default", topics = "topic.quick.default")
    public void listenDefaultTopic(String msgData) {
        log.info("接收自定义主题kafka信息，topic:{},msg:{}","topic.quick.default",msgData);
        log.info("自定义主题kafka消息接收方 demo receive : "+msgData);
    }
}
