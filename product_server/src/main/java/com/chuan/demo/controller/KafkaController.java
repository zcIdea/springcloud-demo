package com.chuan.demo.controller;

import com.chuan.demo.config.KafkaSendResultHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaController
 * @Description: kafka示例
 * @Author: zc
 * @Date 2020/5/26 15:52
 **/
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    //事物
    @Autowired
    private KafkaTemplate tranKafkaTemplate;
    @Resource
    private KafkaTemplate defaultKafkaTemplate;
//    @Autowired
//    private AdminClient adminClient;
    @Autowired
    private KafkaSendResultHandler producerListener;

    @ApiOperation(value = "",notes = "")
    @GetMapping("/kafkaTemplate")
    public String testDemo(@RequestParam("msg") String msg) throws InterruptedException {
        log.info("发送信息到kafka -> msg：{}",msg);
//        kafkaTemplate.send("topic.quick.demo", "this is my first demo");
        kafkaTemplate.send("topic.quick.demo", msg);

        //使用ProducerRecord
        ProducerRecord record=new ProducerRecord("topic.quick.demo", msg);
        kafkaTemplate.send(record);

        //使用Message发送消息
        Map map = new HashMap();
        map.put(KafkaHeaders.TOPIC, "topic.quick.demo");
        map.put(KafkaHeaders.PARTITION_ID, 0);
        map.put(KafkaHeaders.MESSAGE_KEY, 0);
        GenericMessage message = new GenericMessage("use Message to send message",new MessageHeaders(map));
        ListenableFuture send = kafkaTemplate.send(message);

        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
        Thread.sleep(5000);
        return "success";
    }

    /**
     * 注解事务开启
     * @param req
     * @param res
     * @param msg
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendMessageTransactional", method = RequestMethod.GET)
    @Transactional
    public String sendMessage5(HttpServletRequest req,
                               HttpServletResponse res,
                               @RequestParam String msg) throws Exception {

//        tranKafkaTemplate.send("topic.quick.demo", "test transactional annotation");
        tranKafkaTemplate.send("topic.quick.demo", msg);
        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
        Thread.sleep(5000);
        return "success";
    }

    @ApiOperation(value = "",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "",required = true,dataType = "String")
    })
    @GetMapping("/sendDefaultKafkaTemplate")
    public String sendDefaultKafkaTemplate(@RequestParam("msg") String msg) throws Exception{
        log.info("使用kafka自定义主题发送信息 -> msg:{}",msg);
        defaultKafkaTemplate.sendDefault(msg);
        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
        Thread.sleep(5000);
        return "success";
    }


    /**
     * 使用KafkaSendResultHandler实现消息发送结果回调
     *  发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。
     *  主要是因为KafkaTemplate发送消息是采取异步方式发送的.
     * @throws InterruptedException
     */
    @ApiOperation(value = "",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "",required = true,dataType = "String")
    })
    @GetMapping("/sendProducerListen")
    public String sendProducerListen(@RequestParam("msg") String msg) throws InterruptedException {
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.send("topic.quick.demo", msg);
        Thread.sleep(1000);
        return "success";
    }

}
