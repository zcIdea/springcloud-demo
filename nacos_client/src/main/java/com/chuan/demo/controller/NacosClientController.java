package com.chuan.demo.controller;

import com.chuan.demo.api.ClientApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName NacosController
 * @Description: nacos客户端
 * @Author: 张川
 * @Date 2020/5/28 16:54
 **/
@Slf4j
@RestController
public class NacosClientController {

    /**
     * 这里使用了Spring Cloud Common中的LoadBalancerClient接口来挑选服务实例信息。
     *    然后从挑选出的实例信息中获取可访问的URI，拼接上服务提供方的接口规则来发起调用。
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientApi clientApi;

    @GetMapping("/helloClient")
    public String helloClient(@RequestParam String name){
        log.info("NacosClientController helloClient->name:{}",name);

        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos_server");
        String url=serviceInstance.getUri().toString()+ "/hello?name=" + "didi";

//        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return "Invoke : " + url + ", return : " + result;
    }

    @GetMapping("/helloClientToFeign")
    public String helloClientToFeign(@RequestParam String name){
        log.info("NacosClientController helloClientToFeign->name:{}",name);

        String result = clientApi.hello(name);

        return "return : " + result;
    }



}
