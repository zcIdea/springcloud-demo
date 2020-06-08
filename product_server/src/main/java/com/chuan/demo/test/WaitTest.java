package com.chuan.demo.test;

/**
 * wait（）、notify()的使用示例
 * 在调用wait（）、notify()系列方法之前，线程必须要获得该对象的对象级别锁，
 * 即只能在同步方法或同步块中调用wait（）方法、notify()系列方法，进入wait（）方法后，
 * 当前线程释放锁，在从wait（）返回前，线程与其他线程竞争重新获得锁，
 * 执行notify()系列方法的线程退出调用了notifyAll的synchronized代码块的时候后，
 * 他们就会去竞争。如果其中一个线程获得了该对象锁，它就会继续往下执行，在它退出synchronized代码块，
 * 释放锁后，其他的已经被唤醒的线程将会继续竞争获取该锁，一直进行下去，直到所有被唤醒的线程都执行完毕
 * @ClassName VolitileCom
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/5 16:11
 **/
public class WaitTest {

    private static WaitTest waitTest=new WaitTest();

    /**
     * volatile修饰的变量
     */
    public volatile boolean flag=true;

    private WaitTest(){}

    public static WaitTest getVolatileCom(){
        return waitTest;
    }

    public void waitMethod(){
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName()+"；volatileCom.wait() start----------");
                wait();
                System.out.println(Thread.currentThread().getName()+"；volatileCom.wait() end----------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void notifyMethod(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"volatileCom.notify() start----------");
//            notify();
            notifyAll();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"volatileCom.notify() end----------");
        }
    }

    public static void main(String[] args) {
        WaitTest volatileCom = WaitTest.getVolatileCom();
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileCom.waitMethod();
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileCom.notifyMethod();
            }
        }).start();
    }


}
