package com.chuan.demo.dao;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spring data jpa的使用示例，要继承JpaRepository
 */
//@Repository
//public interface GoodsDao extends JpaRepository<Goods,Integer> {
public interface GoodsDao{

    /**
     * .利用下标索引传参，索引参数如下所示，索引值从1开始，查询中 ”?X” 个数需要与方法定义的参数个数相一致，并且顺序也要一致
     * @param id
     * @param goods
     * @return
     */
    @Query("select u from goods u where id>?1 and goods like ?2")
    List<Goods> selectGoodsByParam(int id,String goods);

    /**
     * 命名参数（推荐）：这种方式可以定义好参数名，赋值时采用@Param(“参数名”)，而不用管顺序
     * @param id
     * @param goods
     * @return
     */
    @Query("select u from t_user u where id>:id and username like :name")
    List<Goods> selectGoodsByParam2(@Param("id") int id, @Param("goods") String goods);

}