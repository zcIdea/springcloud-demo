package com.chuan.demo.controller;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.service.GoodsService;
import com.chuan.demo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
     * @return
     */
    @RequestMapping(value = "/queryGoodsInfoList",method = RequestMethod.GET)
    public List<Goods> queryGoodsInfoList(){
        log.info("保存物品信息");
        log.info("GoodsController queryGoodsInfoList");
        List<Goods> goods = goodsService.queryGoodsList();

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
        int i = goodsService.saveGoodsInfo(goods);
        log.info("新增返回结果 --> i:{}",i);
        System.out.println(i);
        return goodsService.queryGoodsList();

    }

    /**
     * @return
     */
    @ApiOperation(value = "queryGoodsAndPersonInfoList",notes = "查询物品人员信息")
    @RequestMapping(value = "/queryGoodsAndPersonInfoList",method = RequestMethod.GET)
    public List<Goods> queryGoodsAndPersonInfoList(){
        log.info("提供方 查询物品人员信息");
        log.info("GoodsController queryGoodsAndPersonInfoList");
        List<Goods> goods = goodsService.queryGoodsAndPersonList();
        List<Goods> goods2 = goodsService.queryGoodsAndPersonList2();
        log.info("提供方 新增返回结果 --> i:{}",goods.get(0).getGoods());
        log.info("提供方 新增返回结果---------987654334432----- --> i:{}",goods2.get(0).getGoods());
        return goods2;

    }

    @ApiOperation(value = "",notes = "")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "主键id",required = true,dataType = "int"),
                        @ApiImplicitParam(name = "revision",value = "版本号",required = true,dataType = "int"),
                        @ApiImplicitParam(name = "updatedBy",value = "更新人",required = true,dataType = "String")})
    @PostMapping("/updateGoodsInfo")
    public String updateGoodsInfo(@RequestParam("id") int id,@RequestParam("revision") int revision,@RequestParam("updatedBy") int updatedBy){

        log.info("更新物品信息");
        log.info("GoodsController updateGoodsInfo->revision:{},updatedBy:{},id:{}",revision,updatedBy,id);
        int i = goodsService.updateGoodsInfo(revision, updatedBy, id);
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
