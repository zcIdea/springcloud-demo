package com.chuan.demo.service.impl;

import com.chuan.demo.dao.GoodsDao;
import com.chuan.demo.dao.GoodsMapper;
import com.chuan.demo.dao.UserDao;
import com.chuan.demo.entity.Goods;
import com.chuan.demo.entity.User;
import com.chuan.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GoodsServiceImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/22 14:12
 **/
@Service
@CacheConfig(cacheNames = "c1")// 配置缓存的名字
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
    private UserDao userDao;

    /**
     * @Cacheable
     *   这个注解一般加在查询方法上，表示将一个方法的返回值缓存起来，默认情况下，缓存的key就是方法的参数，缓存的value就是方法的返回值
     * @param id
     * @return
     */
    @Cacheable(key = "#id")
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

    @Override
    public List<Map<String, Object>> queryGoodsListByJDBC(){

        String sql="select * from goods";
        String sqlUpdate="update goods t set t.name where t.id=1";

//        int num = jdbcTemplate.update(sqlUpdate);

//        List<Goods> goods = jdbcTemplate.queryForList(sql, Goods.class);

        return jdbcTemplate.queryForList(sql);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User selectById(String id){

        String sql = "select * from admin where id=?";

        final User u = new User();

        final Object[] params = new Object[] {id};

        jdbcTemplate.query(sql, params, new RowCallbackHandler(){

            public void processRow(ResultSet rs) throws SQLException {
                u.setId(rs.getLong("ID"));
                u.setName(rs.getString("NAME"));

            }

        });

        return u;

    }

    @Override
    public List<Goods> getAllGoods() {

        //推荐这个，goods表字段要和Goods类属性相同，下划线会转成峰驼式的属性名
        List<Goods> query = jdbcTemplate.query("select * from goods", new BeanPropertyRowMapper<>(Goods.class));

        //这个方法太繁琐，适合多表查询情况
        return jdbcTemplate.query("select * from goods", new RowMapper<Goods>() {
            @Override
            public Goods mapRow(ResultSet resultSet, int i) throws SQLException {
                int personId = resultSet.getInt("person_id");
                String goods = resultSet.getString("goods");
                int id = resultSet.getInt("id");
                Goods goodsBO = new Goods();
                goodsBO.setGoods(goods);
                goodsBO.setPersonId(personId);
                goodsBO.setId(id);
                return goodsBO;
            }
        });
    }


    @Override
    public List<User> getAllUserByJpa() {

//        return userDao.findAll();
        return null;
    }
}
