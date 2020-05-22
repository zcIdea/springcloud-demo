package com.chuan.demo.service;

import com.chuan.demo.entity.Person;

public interface PersonService {

    Person getOne(int id);
    Person getPersonById(int id);

    String queryStringValueForRedis(String key);
}
