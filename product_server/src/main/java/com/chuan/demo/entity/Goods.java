package com.chuan.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName Goods
 * @Description: 物品信息类
 * @Author: 张川
 * @Date 2020/5/22 13:49
 **/
//@Setter
//@Getter
@Data
public class Goods {
    private int id;
    private int personId;
    private String goods;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private int revision;
    private String removed;

    private Person person;
}
