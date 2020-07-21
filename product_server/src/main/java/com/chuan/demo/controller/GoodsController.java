package com.chuan.demo.controller;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.service.GoodsService;
import com.chuan.demo.service.RedisService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestController
 * @Description: test
 * @Author: zhangchuan
 * @Date 2020/5/22 10:05
 **/
@Slf4j
@Api(value = "GoodsController",description = "物品")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Value("${server.port}")
    Integer port;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisService redisService;
    /**
     * ReentrantLock锁
     */
    private static Lock lock=new ReentrantLock();
    private static final String REDIS_KEY="goodsList";
    /**
     * 1.mybatis的使用
     * 2.redis的使用
     * 3.缓冲击穿解决办法（互斥锁）
     * @return
     */
    @RequestMapping(value = "/queryGoodsInfoList",method = RequestMethod.GET)
    public List<Goods> queryGoodsInfoList(){
        log.info("保存物品信息");
        log.info("GoodsController queryGoodsInfoList");

        List<Goods> goods =null;
        //1.从缓冲中获取数据
        Object goodsList = redisService.get(REDIS_KEY);
        if(goodsList!=null){
            //缓冲中有数据，直接返回
            goods= (List<Goods>) goodsList;
        }else{
            //2.缓冲中没有数据，查询数据库，放入到缓冲中
            //尝试获取锁（互斥锁），解决缓冲击穿问题，不会让大量的请求去直接查询数据库，导致数据库承受不了
            if(lock.tryLock()){
                try {
                    goodsList = redisService.get(REDIS_KEY);
                    //双重校验，不加也没关系，无非是多刷几次库
                    if(goodsList==null){
                        goods = goodsService.queryGoodsList();
                        if(CollectionUtils.isEmpty(goods)){
                            //不要返回null，可以返回一个空对象，避免了有可能发生的空指针异常，这样的写法更安全
                            goods= Lists.newArrayList();
                        }else{
                            //放入到缓冲中，设置过期时间(20秒，根据业务需求来设置过期时间)，防止休闲时，大量无用的缓冲数据占用内存资源
                            redisService.set(REDIS_KEY,goods,20L);
                        }
                    }else{
                        //缓冲中有数据，返回数据
                        goods= (List<Goods>) goodsList;
                    }

                } finally {
                    //及时释放锁
                    lock.unlock();
                }
            }else{
                //3.获取不到锁，递归调本方法,自旋，不让线程沉睡，减少cpu的耗能
                //再次查询缓冲
                goodsList = redisService.get(REDIS_KEY);
                //缓冲不存在，递归
                if(goodsList==null){
                    try {
                        //休息一下
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return queryGoodsInfoList();
                }else{
                    //缓冲中有数据，返回数据
                    goods= (List<Goods>) goodsList;
                }
            }
        }

        //测试cache-redis缓存
        Goods one = goodsService.getOne(1);
        String o = (String) redisService.get("c1::1");

        List<Map<String, Object>> mapList = goodsService.queryGoodsListByJDBC();

        List<Goods> allGoods = goodsService.getAllGoods();

        log.info("新增返回结果 --> i:{}",goods.get(0).getGoods());
        return goods;

    }
    /**
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/saveGoodsInfo",method = RequestMethod.POST)
    public List<Goods> saveGoodsInfo(@RequestBody(required = true) Goods goods){
        log.info("保存物品信息");
        log.info("GoodsController saveGoodsInfo--> goods:{}",goods);

        //双删，直接删除缓存，预防数据库成功，缓存失败
        redisService.delete(REDIS_KEY);
        int i = goodsService.saveGoodsInfo(goods);
        redisService.delete(REDIS_KEY);

        log.info("新增返回结果 --> i:{}",i);
        System.out.println(i);
        return goodsService.queryGoodsList();

    }

    /**
     * @return
     */
    @ApiOperation(value = "queryGoodsAndPersonInfoList",notes = "查询物品人员信息")
    @RequestMapping(value = "/queryGoodsAndPersonInfoList",method = RequestMethod.GET)
    public List<Goods> queryGoodsAndPersonInfoList(HttpServletRequest request){
        log.info("提供方 查询物品人员信息");
        log.info("GoodsController queryGoodsAndPersonInfoList");

        //获取请求消息头信息
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames!=null){
            while (headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                log.info("--------------【name】:"+name+";"+"【value】:"+value);
            }
        }

//        List<Goods> goods = goodsService.queryGoodsAndPersonList();
//        List<Goods> goods2 = goodsService.queryGoodsAndPersonList2();
//        log.info("提供方 新增返回结果 --> i:{}",goods.get(0).getGoods());
//        log.info("提供方 新增返回结果---------987654334432----- --> i:{}",goods2.get(0).getGoods());
//        return goods2;
        return null;

    }

    @ApiOperation(value = "",notes = "")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "主键id",required = true,dataType = "int"),
                        @ApiImplicitParam(name = "revision",value = "版本号",required = true,dataType = "int"),
                        @ApiImplicitParam(name = "updatedBy",value = "更新人",required = true,dataType = "String")})
    @PostMapping("/updateGoodsInfo")
    public String updateGoodsInfo(@RequestParam("id") int id,@RequestParam("revision") int revision,@RequestParam("updatedBy") int updatedBy){

        log.info("更新物品信息");
        log.info("GoodsController updateGoodsInfo->revision:{},updatedBy:{},id:{}",revision,updatedBy,id);

        //双删，直接删除缓存，预防数据库成功，缓存失败
        redisService.delete(REDIS_KEY);
        int i = goodsService.updateGoodsInfo(revision, updatedBy, id);
        redisService.delete(REDIS_KEY);

        if(i>0){
            log.info("更新成功！");
            return "success";
        }else{
            log.error("更新失败");
            return "fail";
        }
    }

    @ApiOperation(value = "测试nginx",notes = "接口1")
    @GetMapping("/testNginx")
    public String testNginx(@RequestParam("id") Long userId) {
        log.info("提供方 获取物品信息");
        log.info("提供方 获取的数据-> userId:{}",userId);
        return "提供方方法调用"+userId;

        //ceshi idea提交到GitHub远程仓库

    }


    /**
     * 通过spring session解决session共享问题，会将session数据保存到redis中，每次获取从redis中获取
     * 使用 Spring Session 了，其实就是使用普通的 HttpSession ，其他的 Session 同步到 Redis 等操作，框架已经自动帮你完成了
     * @param session
     * @return
     */
    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "javaboy");
        session.setAttribute("session", "session共享问题解决");
        return String.valueOf(port);
    }
    @GetMapping("/get")
    public String get(HttpSession session) {
        System.out.println(session.getAttribute("session") + ":" + port);
        return session.getAttribute("user") + ":" + port;
    }

}
