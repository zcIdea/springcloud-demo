package com.chuan.demo.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 * @ClassName ProxyTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/11 17:57
 **/
public class ProxyTest {

    public static void main(String[] args) {

        HelloInterface hello=new HelloImpl();
        //代理处理类
        InvocationHandler handler=new ProxyHandler(hello);
        //代理类
        HelloInterface proxyInstance = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);
        proxyInstance.sayHello();

    }
}
