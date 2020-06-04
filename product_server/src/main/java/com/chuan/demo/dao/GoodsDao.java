package com.chuan.demo.dao;

import com.chuan.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * spring data jpa的使用示例，要继承JpaRepository
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
//public interface UserDao {
}