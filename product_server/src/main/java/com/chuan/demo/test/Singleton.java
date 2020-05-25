package com.chuan.demo.test;

/**
 * volatile使用：1.内存可见性；2.禁止指令重排序
 */
public class Singleton{

    private volatile static Singleton instance = null;
//    private static Singleton instance = null;

    private Singleton() {}
     
    public static Singleton getInstance() {
        if(instance==null) {
            //多个线程在这里等着获取锁，有可能多个线程拿到的值是空的
            System.out.println(instance);
            synchronized (Singleton.class) {
                if(instance==null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}