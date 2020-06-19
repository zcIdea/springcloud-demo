package com.chuan.demo.test.blockingQueue;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName BlockingQueueTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/8 20:26
 **/
public class BlockingQueueTest {

    static ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<String>(2);
    static LinkedBlockingQueue<String> linkedBlockingQueue=new LinkedBlockingQueue<String>(2);
//    DelayQueue  delayQueue=new DelayQueue(Collections.singleton(2));

    private static BlockingQueueTest blockingQueueTest = null;

    private BlockingQueueTest() {
    }

    public static BlockingQueueTest getBlockingQueueTest(){
        if(blockingQueueTest==null){
            synchronized (BlockingQueueTest.class){
                if(blockingQueueTest==null){
                    blockingQueueTest = new BlockingQueueTest();
                }
            }
        }
        return blockingQueueTest;
    }

    public void arrayAdd(){
        for (int i = 0; i < 2; i++) {
            System.out.println("插入数据到队列！"+"1"+i);
            boolean add = arrayBlockingQueue.add("1" + i);
        }
        System.out.println("队列长度："+arrayBlockingQueue.size());
        for (int i = 0; i < 2; i++) {
            String remove = arrayBlockingQueue.remove();
            System.out.println("队列长度："+arrayBlockingQueue.size());
            System.out.println("从队列中移除"+remove);
        }
    }
    public void arrayOffer(){
        for (int i = 0; i < 3; i++) {
            System.out.println("插入数据到队列！"+"1"+i);
            boolean offer = arrayBlockingQueue.offer("1" + i);
            System.out.println(offer);
        }
        System.out.println("-------------------队列长度："+arrayBlockingQueue.size());
        for (int i = 0; i < 2; i++) {
            String remove = arrayBlockingQueue.poll();
            System.out.println("队列长度："+arrayBlockingQueue.size());
            System.out.println("从队列中移除"+remove);
        }
    }
    public void arrayPut(){
        for (int i = 0; i < 3; i++) {
            try {
                arrayBlockingQueue.put("1" + i);
                System.out.println("插入数据到队列！"+"1"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------------------队列长度："+arrayBlockingQueue.size());
    }
    public void arrayTake(){
        System.out.println("-------------------队列长度："+arrayBlockingQueue.size());
        String remove = null;
        try {
            remove = arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("从队列中移除"+remove);
        System.out.println("队列长度："+arrayBlockingQueue.size());
    }

    /**
     * 启动一个main函数，就是开启一个进程和一个线程。
     * 进程内的资源是共享的。进程和进程之间的资源不是共享的，是隔离的。
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueueTest blockingQueueTest = BlockingQueueTest.getBlockingQueueTest();
        System.out.println(blockingQueueTest);
//        blockingQueueTest.arrayAdd();
//        blockingQueueTest.arrayOffer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueueTest.arrayPut();
            }
        }).start();

        try {
            System.out.println("睡了");
            Thread.sleep(3000);
            System.out.println("醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueueTest.arrayTake();
            }
        }).start();

    }


}
