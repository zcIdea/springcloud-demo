package com.chuan.demo.api;


import com.chuan.demo.config.HeaderPenetratedInterceptor;
import com.chuan.demo.entity.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "product-server",description = "获取物品信息接口")
//@FeignClient("product-server")
@FeignClient(name = "GoodsApi",url = "${provider.url}",configuration = HeaderPenetratedInterceptor.class)
//在接口上使用 @RequestMapping，并加上 headers 属性
//@RequestMapping(value = "/",headers = {"app=test-app","token=${test-app.token}"})
public interface GoodsApi {

    @ApiOperation(value = "获取物品信息接口",notes = "获取物品信息")
//    @RequestMapping(value = "/goods/queryGoodsAndPersonInfoList",method = RequestMethod.GET,headers = {"app=test-app","token=${test-app.token}"})
    @RequestMapping(value = "/goods/queryGoodsAndPersonInfoList",method = RequestMethod.GET)
//    @RequestMapping(value = "/queryGoodsAndPersonInfoList",method = RequestMethod.GET)
    List<Goods> queryGoodsAndPersonInfoList();


}
