package com.chuan.demo.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    /**
     * 使用AtomicInteger，即使不用同步块synchronized，最后的结果也是1000，
     *     可用看出AtomicInteger的作用，用原子方式更新的int值。
     * 主要用于在高并发环境下的高效程序处理。使用非阻塞算法来实现并发控制。
     */
    public  static AtomicInteger count = new AtomicInteger(0);
      
    public static void inc() {  
   
        //这里延迟1毫秒，使得结果明显  
        try {  
            Thread.sleep(1);  
        } catch (InterruptedException e) {  
        }  
        count.getAndIncrement();  
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