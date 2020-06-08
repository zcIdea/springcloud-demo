package com.chuan.demo.test;

/**
 *
 * 单例模式恶汉模式
 *
 * 在类加载的时候就完成了实例化，避免了多线程的同步问题。当然缺点也是有的，
 *  因为类加载时就实例化了，没有达到Lazy Loading (懒加载) 的效果，如果该实例没被使用，内存就浪费了
 * @ClassName SingletonEH
 * @Description: TODO
 * @Author: 张川
 * @Date 2020/6/5 14:40
 **/
public class SingletonEH {

    private final static SingletonEH singletonEH = new SingletonEH();

    private SingletonEH(){};

    public static SingletonEH getInstance(){
        return singletonEH;
    }

}
