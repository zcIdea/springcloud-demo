package com.chuan.demo.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Person
 * @Description: TODO
 * @Author: wanda
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
