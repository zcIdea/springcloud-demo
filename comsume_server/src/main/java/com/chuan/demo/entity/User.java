package com.chuan.demo.entity;

import java.math.BigDecimal;

/**
 * @ClassName User
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/4/23 17:59
 **/
public class User {

    private Long id;
    private String name;
    private Integer age;
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
