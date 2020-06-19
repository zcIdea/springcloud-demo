package com.chuan.demo.test.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/8 15:58
 **/
public class ReentrantLockTest {

    private ReentrantLock lock=new ReentrantLock();

    public void testLock(String msg){
        try {
            //获取锁
            lock.lock();
            System.out.println(Thread.currentThread().getName()+","+msg);
            System.out.println(msg+",lock获取锁！");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
            System.out.println(msg+"；lock释放锁");
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest=new ReentrantLockTest();
        CountDownLatch downLatch=new CountDownLatch(9);//设置初始值

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLockTest.testLock(finalI +".ceshi");
                    downLatch.countDown();//当前线程调用此方法，则计数减一
                }
            }).start();
        }
        System.out.println("主线程开始执行");
        try {
            downLatch.await();//阻塞当前线程，直到计数器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束执行");

    }

}
