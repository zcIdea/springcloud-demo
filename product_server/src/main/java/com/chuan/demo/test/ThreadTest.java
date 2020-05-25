package com.chuan.demo.test;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ThreadTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/23 16:05
 **/
public class ThreadTest {


    public static void main(String[] args) {

        ThreadAndVoliate threadAndVoliate=new ThreadAndVoliate();
        new Thread(threadAndVoliate).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadAndVoliate.setFlag(false);

        /*for (int i = 0; i < 100; i++) {
            threadAndVoliate.setStringV("huhu"+i);
            new Thread(threadAndVoliate).start();
        }*/
        //CountDownLatch 让线程一起执行
        /*CountDownLatch downLatch=new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println("Singleton-->"+Singleton.getInstance());
                    downLatch.countDown();
                }
            }.start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
