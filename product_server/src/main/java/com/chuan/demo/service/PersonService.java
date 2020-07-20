package com.chuan.demo.service;

import com.chuan.demo.entity.PersonMongoDb;

import java.util.List;

public interface PersonService {

    PersonMongoDb getOne(int id);
    PersonMongoDb getPersonById(int id);

    String queryStringValueForRedis(String key);
    int saveUserInfo(PersonMongoDb person);

    List<PersonMongoDb> selectPersonAndGoods();
    List<PersonMongoDb> selectPersonAndGoods2();
}
