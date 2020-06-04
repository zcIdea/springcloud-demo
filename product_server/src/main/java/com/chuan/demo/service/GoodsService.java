package com.chuan.demo.service;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    /**
     *
     * @param id
     * @return
     */
    Goods getOne(int id);

    /**
     *
     * @return
     */
    List<Goods> queryGoodsList();

    /**
     *
     * @param goods
     * @return
     */
    int saveGoodsInfo(Goods goods);

    /**
     *
     * @param revision
     * @param updatedBy
     * @param id
     * @return
     */
    int updateGoodsInfo(@Param("revision") int revision, @Param("updatedBy") int updatedBy, @Param("id") int id);

    /**
     *
     * @return
     */
    List<Goods> queryGoodsAndPersonList();
    /**
     *
     * @return
     */
    List<Goods> queryGoodsAndPersonList2();

    /**
     * jdbcTemplate的使用示例
     * @return
     */
    List<Map<String, Object>> queryGoodsListByJDBC();

    /**
     *
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     *
     * @return
     */
    List<Goods> getAllGoods();

    /**
     * spring data jap使用
     * Goods要和数据库中对应的表写注解，匹配起来
     * @return
     */
    List<User> getAllUserByJpa();
}
