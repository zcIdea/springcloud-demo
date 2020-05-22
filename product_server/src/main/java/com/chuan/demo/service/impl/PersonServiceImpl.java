package com.chuan.demo.service.impl;

import com.chuan.demo.dao.PersonMapper;
import com.chuan.demo.entity.Person;
import com.chuan.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PersonServiceImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/13 15:04
 **/
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Person getOne(int id) {
        return personMapper.getOne(id);
    }

    @Override
    public Person getPersonById(int id) {
        return personMapper.getPersonById(id);
    }

    @Override
    public String queryStringValueForRedis(String key) {
        //判断key是否存在
        Boolean aBoolean = redisTemplate.hasKey(key);
        if(!aBoolean){
            return null;
        }
        //查询key的String类型的值
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public int saveUserInfo(Person person) {
        return personMapper.saveUserInfo(person);
    }

    @Override
    public List<Person> selectPersonAndGoods() {
        return personMapper.selectPersonAndGoods();
    }
    @Override
    public List<Person> selectPersonAndGoods2() {
        return personMapper.selectPersonAndGoods2();
    }
}
