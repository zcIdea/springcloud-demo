package com.chuan.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description: test
 * @Author: zhangchuan
 * @Date 2020/5/22 10:05
 **/
@Api(value = "TestController",description = "测试类")
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    /**
     * 测试方法，git提交
     * @param name
     * @return
     */
    @ApiOperation(value = "testMethod",notes = "测试方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "name",value = "姓名",required = true,dataType = "String")})
    @RequestMapping(value = "/testMethod",method = RequestMethod.GET)
//    @GetMapping("/testMethod")
    public String testMethod(@RequestParam(value = "name",required = true) String name){
        log.info("测试方法，git提交");
        log.info("TestController testMethod name:{}",name);
        log.info("测试方法，git提交,返回参数 String：{}",name+":hello gitHub");
        return name+":hello gitHub";
    }


}
