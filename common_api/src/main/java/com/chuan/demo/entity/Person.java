package com.chuan.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Person
 * @Description: 人员信息类
 * @Author: 张川
 * @Date 2020/5/13 14:57
 **/
@Data
public class Person {

    private int id;
    private String name;
    private String sex;
    private int age;
    private List<Goods> goodsList;

    @Override
    public String toString() {
        return "person{id:"+id+",name:"+name+",sex:"+sex+",age:"+age+"}";
    }
}
