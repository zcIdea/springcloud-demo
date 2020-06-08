package com.chuan.demo.test;

/**
 *
 * 单例模式-懒汉式
 * 1.普通的懒汉式 (线程不安全，不可用)
 * 2.同步方法的懒汉式 (可用)
 * 3.双重检查懒汉式 (可用，推荐)
 * 4.静态内部类 (可用，推荐)
 *
 * @Author: zc
 * @Date 2020/6/5 14:43
 **/
public class SingletonLH {

    private static SingletonLH instance = null;
    private SingletonLH(){}

    /**
     * 普通的懒汉式 (线程不安全，不可用)
     * @return
     */
    public static SingletonLH getInstance(){
        if (instance==null){
            instance = new SingletonLH();
        }
        return instance;
    }

    /**
     * 同步方法的懒汉式 (可用)
     * 这种写法是对getInstance()加了锁的处理，保证了同一时刻只能有一个线程访问并获得实例，
     *  但是缺点也很明显，因为synchronized是修饰整个方法，
     *   每个线程访问都要进行同步，而其实这个方法只执行一次实例化代码就够了，每次都同步方法显然效率低下
     * @return
     */
    public static SingletonLH getInstance1(){
        synchronized (SingletonLH.class){
            if (instance==null){
                instance = new SingletonLH();
            }
        }
        return instance;
    }

    /**
     * 双重检查懒汉式 (可用，推荐)
     * @return
     */
    public static SingletonLH getInstance2(){
        if(instance==null){
            synchronized (SingletonLH.class){
                if (instance==null){
                    instance = new SingletonLH();
                }
            }
        }
        return instance;
    }


    /**
     * 静态内部类 (可用，推荐)
     */
    private static class SingletonInstance {
        private static final SingletonLH INSTANCE = new SingletonLH();
    }

    public static SingletonLH getInstance3() {
        return SingletonInstance.INSTANCE;
    }

}
