package com.chuan.demo.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * ConsumerRecord 类里面包含分区信息、消息头、消息体等内容，如果业务需要获取这些参数时，
 *   使用ConsumerRecord会是个不错的选择
 */
//@Component
public class SingleListener {

    private static final Logger log = LoggerFactory.getLogger(SingleListener.class);

    @KafkaListener(id = "consumer", topics = "topic.quick.consumer")
    public void consumerListener(ConsumerRecord<Integer, String> record) {
        log.info("topic.quick.consumer receive : " + record.toString());
    }
}