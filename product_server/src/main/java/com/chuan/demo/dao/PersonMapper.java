package com.chuan.demo.dao;

import com.chuan.demo.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface PersonMapper {

    @Select("select * from person where id=#{id}")
    Person getOne(@Param("id") int id);

    Person getPersonById(@Param("id") int id);

    int saveUserInfo(Person person);

    List<Person> selectPersonAndGoods();

    List<Person> selectPersonAndGoods2();
}
