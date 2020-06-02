package com.chuan.demo.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义信道
 */
public interface MySource {

    @Output("myOutput")
    MessageChannel myOutput();

}