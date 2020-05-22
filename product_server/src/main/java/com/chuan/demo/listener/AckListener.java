package com.chuan.demo.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用Ack机制确认消费
 *
 * 使用Kafka的Ack机制比较简单，只需简单的三步即可：
 * 1.设置ENABLE_AUTO_COMMIT_CONFIG=false，禁止自动提交
 * 2.设置AckMode=MANUAL_IMMEDIATE
 * 3.监听方法加入Acknowledgment ack 参数
 */
//@Component
public class AckListener {

    private static final Logger log= LoggerFactory.getLogger(AckListener.class);

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean("ackContainerFactory")
    public ConcurrentKafkaListenerContainerFactory ackContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        return factory;
    }


    @KafkaListener(id = "ack", topics = "topic.quick.ack",containerFactory = "ackContainerFactory")
    public void ackListener(ConsumerRecord record, Acknowledgment ack) {
        log.info("topic.quick.ack receive : " + record.value());
        ack.acknowledge();
    }

    @KafkaListener(id = "ack", topics = "topic.quick.ack", containerFactory = "ackContainerFactory")
    public void ackListener(ConsumerRecord record, Acknowledgment ack, Consumer consumer) {
        log.info("topic.quick.ack receive : " + record.value());

        //如果偏移量为偶数则确认消费，否则拒绝消费
        if (record.offset() % 2 == 0) {
            log.info(record.offset()+"--ack");
            ack.acknowledge();
        } else {
            log.info(record.offset()+"--nack");
//            kafkaTemplate.send("topic.quick.ack", record.value());
        }
    }

    @KafkaListener(id = "ack", topics = "topic.quick.ack", containerFactory = "ackContainerFactory")
    public void ackListener1(ConsumerRecord record, Acknowledgment ack, Consumer consumer) {
        log.info("topic.quick.ack receive : " + record.value());

        //如果偏移量为偶数则确认消费，否则拒绝消费
        if (record.offset() % 2 == 0) {
            log.info(record.offset()+"--ack");
            ack.acknowledge();
        } else {
            log.info(record.offset()+"--nack");
            consumer.seek(new TopicPartition("topic.quick.ack",record.partition()),record.offset() );
        }
    }
}
