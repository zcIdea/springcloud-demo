package com.chuan.demo.service.impl;

import com.chuan.demo.dao.GoodsMapper;
import com.chuan.demo.entity.Goods;
import com.chuan.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/22 14:12
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * @param id
     * @return
     */
    @Override
    public Goods getOne(int id) {
        return goodsMapper.getOne(id);
    }

    /**
     * @return
     */
    @Override
    public List<Goods> queryGoodsList() {
        return goodsMapper.queryGoodsList();
    }

    /**
     * @param goods
     * @return
     */
    @Override
    public int saveGoodsInfo(Goods goods) {
        return goodsMapper.saveGoodsInfo(goods);
    }

    /**
     * @param revision
     * @param updatedBy
     * @param id
     * @return
     */
    @Override
    public int updateGoodsInfo(int revision, int updatedBy, int id) {
        return goodsMapper.updateGoodsInfo(revision,updatedBy,id);
    }

    /**
     * @return
     */
    @Override
    public List<Goods> queryGoodsAndPersonList() {
        return goodsMapper.queryGoodsAndPersonList();
    }
    /**
     * @return
     */
    @Override
    public List<Goods> queryGoodsAndPersonList2() {
        return goodsMapper.queryGoodsAndPersonList2();
    }
}
