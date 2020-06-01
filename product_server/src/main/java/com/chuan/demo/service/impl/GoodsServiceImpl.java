package com.chuan.demo.service.impl;

import com.chuan.demo.dao.GoodsMapper;
import com.chuan.demo.entity.Goods;
import com.chuan.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//    @Transactional(rollbackFor = RuntimeException.class)
    @Transactional              //事物管理，保证数据的一致性
    @Override
    public int saveGoodsInfo(Goods goods) {
        int i = goodsMapper.saveGoodsInfo(goods);

        /*if(true){
            System.out.println("抛出异常，测试事物管理");
            throw new RuntimeException("抛出运行时异常,测试事物管理，事物回滚");
        }else{
            return i;
        }*/
        return i;
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
