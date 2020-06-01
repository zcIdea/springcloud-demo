package com.chuan.demo.controller;

import com.chuan.demo.config.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * 访问地址 http://localhost:8766/index.html
 * @author 张川
 */
@RestController
public class Controller {
    static Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/ws/sendAll", method = RequestMethod.GET)
    public String sendAllMessage(@RequestParam(required = true) String message) {
        webSocketServer.broadCastInfo(message);
        return "success";
    }

    /**
     * 指定会话ID发消息
     *
     * @param message 消息内容
     * @param userId      连接会话ID
     * @return
     */
    @RequestMapping(value = "/ws/sendOne", method = RequestMethod.GET)
    public String sendOneMessage(@RequestParam(required = true) String message,
                                 @RequestParam(required = true) String userId) {
        webSocketServer.sendToUser(userId, message);
        return "success";
    }
    /**
     * 指定会话ID发消息
     * 发送信息给指定用户，实现在线聊天模式
     * @param message 消息内容
     * @param toUserId  连接会话ID
     * @return
     */
    @RequestMapping(value = "/ws/sendOneUserId", method = RequestMethod.GET)
    public String sendOneMessageTOUserId(@RequestParam(required = true) String message,
                                 @RequestParam(required = true) String toUserId) {
        webSocketServer.sendToUser(toUserId, message);
        return "success";
    }

    /**
     * 指定会话ID发消息
     * 发送信息给指定用户，实现在线聊天模式
     * @param message 消息内容
     * @param toUserId  连接会话ID
     * @return
     */
    @RequestMapping(value = "/ws/sendOneUserIdAndMe", method = RequestMethod.GET)
    public String sendOneMessageTOUserIdAndMe(@RequestParam(required = true) String message,
                                              @RequestParam(required = true) String userId,
                                              @RequestParam(required = true) String toUserId) {
        webSocketServer.sendToUserAndMe(userId,toUserId, message);
        return "success";
    }
}