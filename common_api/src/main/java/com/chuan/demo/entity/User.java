package com.chuan.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName User
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/4/23 17:59
 **/
@Entity
@Table(name = "uesr")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    @Column(name="id")//数据库字段名
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Integer age;
    @Column(name="money")
    private BigDecimal money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
