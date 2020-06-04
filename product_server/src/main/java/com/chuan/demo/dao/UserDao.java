package com.chuan.demo.dao;

import com.chuan.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spring data jpa的使用示例，要继承JpaRepository
 */
//@Repository
//public interface UserDao extends JpaRepository<User,Long> {
public interface UserDao {


    @Query("select u from t_user u where id=(select max(id) from t_user)")
    User getMaxIdUser();

    /**
     * 利用下标索引传参，索引参数如下所示，索引值从1开始，查询中 ”?X” 个数需要与方法定义的参数个数相一致，并且顺序也要一致
     * @param id
     * @param name
     * @return
     */
    @Query("select u from t_user u where id>?1 and username like ?2")
    List<User> selectUserByParam(Long id, String name);

    /**
     * 命名参数（推荐）：这种方式可以定义好参数名，赋值时采用@Param(“参数名”)，而不用管顺序
     * @param name
     * @param id
     * @return
     */
    @Query("select u from t_user u where id>:id and username like :name")
    List<User> selectUserByParam2(@Param("name") String name, @Param("id") Long id);

    @Query(value = "select * from t_user",nativeQuery = true)
    List<User> selectAll();

    /**
     * 涉及到数据修改操作，可以使用 @Modifying 注解，@Query 与 @Modifying 这两个 annotation一起声明，可定义个性化更新操作
     * @param age
     * @param id
     * @return
     */
    @Modifying
    @Query("update t_user set age=:age where id>:id")
    int updateUserById(@Param("age") Long age, @Param("id") Long id);
}