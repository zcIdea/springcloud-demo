package com.chuan.demo.controller;

import com.chuan.demo.api.GoodsApi;
import com.chuan.demo.api.TestClient;
import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.User;
import com.chuan.demo.service.UserService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Swagger注解，形成api接口
@Api(value = "UserController",description = "服务消费方用户接口！")
@Slf4j
@RestController
public class UserController {
    @Autowired
    RestTemplate restTemplate; //spring 提供的RestTemplate,方便调用Rest接口,远程调用对象

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @Autowired
    private TestClient testClient;

    @Autowired
    private GoodsApi goodsApi;

    @ApiOperation(value = "获取用户信息-消费方",notes = "接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")})
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {

        log.info("获取用户信息 id：{}",id);
//        User user = restTemplate.getForObject("http://localhost:8080/" + id, User.class);
//        String userName = restTemplate.getForObject("http://localhost:8080/" + id, String.class);

        String userInfo = userService.getUserById(id);
        System.out.println(userInfo);

        //通过指定的服务提供方获取eurekaClient客户端对象，从而获取服务提供方的信息
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("product-server", false);
        Map urlVariables = new HashMap();
        urlVariables.put("hostName", instanceInfo.getHostName());
        urlVariables.put("port", instanceInfo.getPort());
        //执行远程调用
        String userName  = restTemplate.getForObject("http://{hostName}:{port}/getUser/" + id, String.class,urlVariables);
        String userList  = restTemplate.getForObject("http://{hostName}:{port}/getUserList", String.class,urlVariables);

            System.out.println(userList);

        User user = new User();
        user.setName(userName);

        //获取服务名称 eureka-获取服务列表
        List<String> serviceNames = discoveryClient.getServices();
        for (String serviceName : serviceNames) {
            //获取服务中的实例列表
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            for (int i = 0; i < serviceInstances.size(); i++) {
                ServiceInstance serviceInstance = serviceInstances.get(i);
                URI uri = serviceInstance.getUri();
                System.out.println(uri.getPath());
            }
        }

        return user;
    }

    @ApiOperation(value = "获取用户信息-消费方1",notes = "接口1")
//    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")})
    @GetMapping("/getUser")
    public String getUser(@RequestParam("id") Long userId) {

        String userInfo = userService.getUserById(userId);
        System.out.println(userInfo);
        return userInfo;
    }

    @ApiOperation(value = "获取用户信息-消费方1",notes = "接口1")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")})
    @GetMapping("/getUserList")
    public String getUserList(@RequestParam("id") Long userId) {

        List<String> userList = testClient.getUserList(userId, "8088080");
        String userInfo = (String) userList.get(0);
        System.out.println(userInfo);
        return userInfo;
    }

    @ApiOperation(value = "获取物品信息",notes = "接口1")
    @GetMapping("/queryGoodsAndPersonInfoList")
    public List<Goods> queryGoodsAndPersonInfoList(@RequestParam("id") Long userId) {
        log.info("获取物品信息");
        log.info("UserController queryGoodsAndPersonInfoList->userId:{}",userId);
        List<Goods> goods = goodsApi.queryGoodsAndPersonInfoList();
        log.info("获取的数据-> goodsList:{}",goods);
        return goods;
    }

    public static void main(String[] args) {
        //日志打印输出格式
        String id="00000";
        String name="ddddd";
        log.info("获取用户信息 id：{},name：{}",id,name);
        log.info("UserController main：id->{},name：{}",id,name);
        log.info("获取用户信息返回结果 result：{}",id+name);
    }
}