package com.chuan.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName WebSocketApi
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/16 17:43
 **/
//@FeignClient(name = "WebSocketApi",url = "${provider.webSocket-url}")
@FeignClient(name = "WebSocketApi",url = "${provider.webSocket-live-url}")
public interface WebSocketApi {


    @RequestMapping(value = "/ws/sendOneUserIdAndMe", method = RequestMethod.GET)
    String sendOneMessageTOUserIdAndMe(@RequestParam(required = true) String message,
                                              @RequestParam(required = true) String userId,
                                              @RequestParam(required = true) String toUserId);


    /**
     * 群发消息内容
     * @param message
     * @return
     */
    @RequestMapping(value = "/ws/sendAll", method = RequestMethod.GET)
    String sendAllMessage(@RequestParam(name = "message",required = true) String message);
    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/ws/sendAllMessageAndHouse", method = RequestMethod.GET)
    public String sendAllMessageAndHouse(@RequestParam(name = "message",required = true) String message,
                                         @RequestParam(name = "houseId",required = true) String houseId);

    /**
     * 消息内容指定用户
     * @return
     */
    @RequestMapping(value = "/ws/sendAllMessageAndHouse", method = RequestMethod.POST)
    public String sendMessageByUserId(@RequestParam(name = "userId",required = true) String userId,
                                         @RequestParam(name = "chat",required = true) String chat);
}
