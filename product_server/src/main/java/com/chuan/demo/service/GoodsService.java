package com.chuan.demo.service;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
