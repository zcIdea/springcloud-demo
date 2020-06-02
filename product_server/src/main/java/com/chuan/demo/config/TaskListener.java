package com.chuan.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 禁止KafkaListener自启动（AutoStartup）
 * 编写两个定时任务，一个晚上12点，一个早上10点
 * 分别在12点的任务上启动KafkaListener，在10点的任务上关闭KafkaListener
 */
@Component
@EnableScheduling
@Slf4j
public class TaskListener{


    @Autowired
    private KafkaListenerEndpointRegistry registry;

    /*@Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(consumerFactory);
        //禁止自动启动
        container.setAutoStartup(false);
        return container;
    }*/

//    @KafkaListener(id = "durable", topics = "topic.quick.durable",containerFactory = "delayContainerFactory")
    @KafkaListener(id = "durable", topics = "topic.quick.durable",containerFactory = "kafkaListenerContainerFactory")
    public void durableListener(String data) {
        //这里做数据持久化的操作
        log.info("topic.quick.durable receive : " + data);
    }


    /**
     * cron表达式的长度为6个或者是7个
     * 依次为：秒、分、时、日、月、周、年（可选）
     *
     * 秒（0~59） 分钟（0~59） 小时（0~23） 日（0~31） 月（0~11） 星期（1~7 1为SUN-依次为SUN，MON，TUE，WED，THU，FRI，SAT）
     */
    //定时器，每天凌晨0点开启监听
//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 7 17 * * ?")
    public void startListener() {
        log.info("开启监听");
        //判断监听容器是否启动，未启动则将其启动
        if (!registry.getListenerContainer("durable").isRunning()) {
            //启动监听容器
            registry.getListenerContainer("durable").start();
        }
        //恢复监听容器
        registry.getListenerContainer("durable").resume();
    }

    //定时器，每天早上10点关闭监听
//    @Scheduled(cron = "0 0 10 * * ?")
    @Scheduled(cron = "0 8 17 * * ?")
    public void shutDownListener() {
        log.info("关闭监听");
        registry.getListenerContainer("durable").pause();
    }



}

