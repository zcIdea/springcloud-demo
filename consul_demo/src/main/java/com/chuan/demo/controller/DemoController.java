package com.chuan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * consul服务注册配置中心使用事例
 * @author zhangchuan
 * @date zhangChuan
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/demoMethod")
    public String demoMethod(){
        return "consul_demo";
    }

    /**
     * 获取所有服务
     * @return
     */
    @GetMapping("/service")
    public Object service(){
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-demo");
        return instances;
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     * @return
     */
    @GetMapping("/discover")
    public Object discover(){
        return loadBalancerClient.choose("consul-demo").getUri().toString();
    }

    /**
     * 调用具体的服务
     * @return
     */
    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-demo");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/demoMethod", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }




}
