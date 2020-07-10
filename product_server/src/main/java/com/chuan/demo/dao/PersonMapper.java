package com.chuan.demo.dao;

import com.chuan.demo.entity.PersonMongoDb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface PersonMapper {

    @Select("select * from person where id=#{id}")
    PersonMongoDb getOne(@Param("id") int id);

    PersonMongoDb getPersonById(@Param("id") int id);

    int saveUserInfo(PersonMongoDb person);

    List<PersonMongoDb> selectPersonAndGoods();

    List<PersonMongoDb> selectPersonAndGoods2();
}
