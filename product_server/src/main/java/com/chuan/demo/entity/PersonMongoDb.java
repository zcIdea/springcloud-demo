package com.chuan.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @ClassName Person
 * @Description: 人员信息类
 * @Author: 张川
 * @Date 2020/5/13 14:57
 **/

@Document( collection = "t_person")
@Data
public class PersonMongoDb {

    private int id;
    private String name;
    private String sex;
    private Integer age;

    @Override
    public String toString() {
        return "person{id:"+id+",name:"+name+",sex:"+sex+",age:"+age+"}";
    }
}
