package com.chuan.demo.test.proxy;

/**
 * @ClassName HelloImpl
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/11 17:58
 **/
public class HelloImpl implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("hello 你好！");
    }
}
