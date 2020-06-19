package com.chuan.demo.test.proxy;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName ProxyHandler
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/11 17:53
 **/
public class ProxyHandler implements InvocationHandler {

    //目标对象，被代理对象
    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    //代理对象调用目标对象方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("目标对象调用前 "+method.getName());
        method.invoke(target,args);
        System.out.println("目标对象调用前 "+method.getName());
        return null;
    }
}
