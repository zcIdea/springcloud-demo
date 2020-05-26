package com.chuan.demo.controller;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private GoodsService goodsService;

    /**
     * @return
     */
    @RequestMapping(value = "/queryGoodsInfoList",method = RequestMethod.GET)
    public List<Goods> queryGoodsInfoList(){
        log.info("保存物品信息");
        log.info("GoodsController queryGoodsInfoList");
        List<Goods> goods = goodsService.queryGoodsList();
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

}
