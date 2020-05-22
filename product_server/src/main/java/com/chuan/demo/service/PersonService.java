package com.chuan.demo.service;

import com.chuan.demo.entity.Person;

import java.util.List;

public interface PersonService {

    Person getOne(int id);
    Person getPersonById(int id);

    String queryStringValueForRedis(String key);
    int saveUserInfo(Person person);

    List<Person> selectPersonAndGoods();
    List<Person> selectPersonAndGoods2();
}
