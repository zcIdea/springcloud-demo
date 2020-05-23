package com.chuan.demo.controller;

import com.chuan.demo.entity.Person;
import com.chuan.demo.service.PersonService;
import com.chuan.demo.service.RedisService;
import com.chuan.demo.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "UserController",description = "测试Swagger")
@RestController
public class UserController {
    /*@Autowired
    UserDao userDao;
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userDao.findById(id);
    }*/

    @Autowired
    PersonService personService;
    @Autowired
    RedisService redisService;
    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")})
    @GetMapping("/getUser/{id}")
    public String getUserById(@PathVariable Long id) {
        return "hello ==="+id;
    }

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "name",value = "用户名",required = true,dataType = "String")
    })
    @GetMapping("/getUserList")
    public List<String> getUserList(@RequestParam("id") Long id, @RequestParam("name") String name) {
        List<String> responseInfo=new ArrayList<String>();
        responseInfo.add("hah:"+id);
        responseInfo.add("huh:"+name);
        responseInfo.add("lal");
        return responseInfo;
    }

    @ApiOperation(value = "获取人员信息",notes = "获取人员信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "name",value = "用户名",required = true,dataType = "String")
    })
    @GetMapping("/getPersonList")
    public Person getPersonList(@RequestParam("id") int id,@RequestParam("name") String name) {
        Person one = personService.getOne(id);
        Person person = personService.getPersonById(id);
        System.out.printf("person"+person.toString());

        String s = personService.queryStringValueForRedis("hah");
        System.out.println(s);

        boolean hhhhh = redisService.hasKey("hhhhh");
        boolean jjjj = redisUtil.hasKey("jjjj");

        return one;
    }
}