package com.chuan.demo.dao;

import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
     *
     * @param id
     * @return
     */
    @Select("select * from goods where id=#{id}")
    Goods getOne(@Param("id") int id);

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
    int updateGoodsInfo(@Param("revision") int revision,@Param("updatedBy") int updatedBy,@Param("id") int id);

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
