package com.chuan.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoListener {

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

    @KafkaListener(id = "err", topics = "topic.quick.error", errorHandler = "consumerAwareErrorHandler")
    public void errorListener(String data) {
        log.info("topic.quick.error  receive : " + data);
        throw new RuntimeException("fail");
    }

    /**
     * 单消息消费异常处理器
     * @return
     */
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                log.info("consumerAwareErrorHandler receive : "+message.getPayload().toString());
                return null;
            }
        };
    }


    /**
     * 使用指定的kafka容易工厂
     * @param msgData
     */
    @KafkaListener(id = "default", topics = "topic.quick.default",containerFactory = "filterContainerFactory")
    public void listenTopicContainerFactory (String msgData) {
        log.info("接收自定义主题kafka信息，topic:{},msg:{}","topic.quick.default",msgData);
        log.info("自定义主题kafka消息接收方 demo receive : "+msgData);
    }




}
