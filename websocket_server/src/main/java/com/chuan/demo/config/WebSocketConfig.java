package com.chuan.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * 开启WebSocket支持
 * @author 张川
 *
 *
 *  参考网址：https://blog.csdn.net/moshowgame/article/details/80275084
 * 如果tomcat部署一直报这个错，请移除 WebSocketConfig 中@Bean ServerEndpointExporter 的注入 。
 * ServerEndpointExporter 是由Spring官方提供的标准实现，用于扫描ServerEndpointConfig配置类和@ServerEndpoint注解实例。
 *  使用规则也很简单：
 *   1.如果使用默认的嵌入式容器 比如Tomcat 则必须手工在上下文提供ServerEndpointExporter。
 *   2.如果使用外部容器部署war包，则不需要提供提供ServerEndpointExporter，
 *        因为此时SpringBoot默认将扫描服务端的行为交给外部容器处理，所以线上部署的时候要把WebSocketConfig中这段注入bean的代码注掉。
 *
 */

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}