package com.chuan.demo.test;

import java.util.concurrent.CountDownLatch;

public class Counter {
//    public volatile  static int count = 0;
    public static int count = 0;

    public static void inc() {  
        //这里延迟5毫秒，使得结果明显  
        try {  
            Thread.sleep(5);  
        } catch (InterruptedException e) {  
        }  
        synchronized(Counter.class) {
            count ++;  
        }
    }  
   
    public static void main(String[] args) throws InterruptedException {  
         final CountDownLatch latch = new CountDownLatch(1000);
        //同时启动1000个线程，去进行i++计算，看看实际结果  
        for (int i = 0; i < 1000; i++) {  
            new Thread(new Runnable() {  
                @Override  
                public void run() {  
                    Counter.inc();  
                    latch.countDown();  
                }  
            }).start();  
        }  
        latch.await();  
        //这里每次运行的值都有可能不同,可能为1000  
        System.out.println("运行结果:Counter.count=" + Counter.count);  
    }  
}  