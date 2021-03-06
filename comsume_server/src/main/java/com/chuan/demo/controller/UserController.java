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
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RList;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedissonClient redissonClient;

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
        log.info("消费方 获取物品信息");
        log.info("UserController queryGoodsAndPersonInfoList->userId:{}",userId);
        List<Goods> goods = goodsApi.queryGoodsAndPersonInfoList();
        log.info("消费方 获取的数据-> goodsList:{}",goods);
        return goods;
    }

    /**
     * 解决跨域问题 @CrossOrigin(value = "http://localhost:8080")
     * cors_demo项目中的 hello.html
     * 也可实现 WebMvcConfigurer接口的addCorsMappings方法，来配置跨域请求
     * @param userId
     * @return
     */
    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "测试nginx",notes = "接口1")
    @GetMapping("/testNginx")
    public String testNginx(@RequestParam("id") Long userId) {
        log.info("消费方 获取物品信息");
        log.info("消费方 获取的数据-> userId:{}",userId);
        return "消费方方法调用"+userId;
    }

    /**
     * 通过统一配置类来解决跨域问题（WebMvcConfigurer接口的addCorsMappings方法）
     * @return
     */
//    @CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "测试Post",notes = "接口1")
    @PostMapping("/testPost")
    public String testPost() {

        //列表（List）
        RList<Object> list = redissonClient.getList("anyList");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        log.info("get(0):{}",list.get(0));
        boolean d = list.remove("D");

        //列表（List）
        RQueue<Object> queue = redissonClient.getQueue("anyQueue");
        queue.add("queue_A");
        queue.add("queue_B");
        queue.add("queue_C");
        log.info((String) queue.peek());
        log.info((String) queue.poll());

        //阻塞队列（Blocking Queue）
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("anyQueue");
        queue.offer("queue_A");
        log.info((String) blockingQueue.peek());
        log.info((String) blockingQueue.poll());
        try {
            blockingQueue.poll(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String userId="231313213";
        log.info("消费方 获取物品信息");
        log.info("消费方 获取的数据-> userId:{}",userId);
        return "消费方方法调用"+userId;
    }

}