package com.chuan.demo.controller;

import com.chuan.demo.entity.PersonMongoDb;
import com.chuan.demo.service.PersonService;
import io.swagger.annotations.*;
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
@Api(value = "TestController",description = "测试类")
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PersonService personService;

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

    /**
     *
     * @param person
     * @return
     */
    @RequestMapping(value = "/savePersonInfo",method = RequestMethod.POST)
    public PersonMongoDb saveUserInfo(@RequestBody(required = true) PersonMongoDb person){
        log.info("保存人员信息");
        log.info("TestController savePersonInfo--> person:{}",person);
        int i = personService.saveUserInfo(person);
        log.info("新增返回结果 --> i:{}",i);
        System.out.println(i);
        return person;

    }


    /**
     * @ApiResponses：用于表示一组响应
     * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
     * @return
     */
    @ApiOperation(value = "selectPersonAndGoods",notes = "查询人员物品信息")
    @ApiResponses({@ApiResponse(code = 200,message = "success"),
                   @ApiResponse(code = 400,message = "error")})
    @RequestMapping(value = "/selectPersonAndGoods",method = RequestMethod.GET)
    public List<PersonMongoDb> selectPersonAndGoods(){
        log.info("查询人员物品信息");
        log.info("TestController selectPersonAndGoods");
        List<PersonMongoDb> personList = personService.selectPersonAndGoods();
        List<PersonMongoDb> personList2 = personService.selectPersonAndGoods2();
        log.info("查询返回结果 --> personList:{}",personList);
        return personList2;

    }

}
