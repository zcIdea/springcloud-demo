package com.chuan.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/1 14:25
 **/
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        for (int i = 0; i < 10; i++) {
            //submit方法有返回值
            Future<String> future = executorService.submit(new CallableThread(i));
            resultList.add(future);

            executorService.execute(new RunnableThread(i));

            new Thread(new RunnableThread(i)).start();
        }
        /**
         * 可以关闭 ExecutorService，这将导致其拒绝新任务。提供两个方法来关闭 ExecutorService。
         * shutdown() 方法在终止前允许执行以前提交的任务，
         * shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。在终止时执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务。关闭未使用的 ExecutorService 以允许回收其资源。
         * 一般分两个阶段关闭 ExecutorService。第一阶段调用 shutdown 拒绝传入任务，然后调用 shutdownNow（如有必要）取消所有遗留的任务
         */
        executorService.shutdown();

        for (Future<String> future:resultList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {

                executorService.shutdownNow();

                e.printStackTrace();

                return;
            }

        }
    }


    static class CallableThread implements Callable<String> {

        private int id;

        public CallableThread(int id) {
            this.id = id;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
            if (new Random().nextBoolean())
                throw new CallableException("Meet error in task." + Thread.currentThread().getName());
            // 一个模拟耗时的操作
            for (int i = 999999999; i > 0; i--)
                ;
            return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
        }
    }
    static class RunnableThread implements Runnable {

        private int id;

        public RunnableThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Runnable()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
            if (new Random().nextBoolean()){
                System.out.println("Meet error in Runnable." + Thread.currentThread().getName());
            }
            // 一个模拟耗时的操作
            for (int i = 999999999; i > 0; i--){
                ;
            }
            System.out.println( "Runnable()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName());
        }
    }

    static class CallableException extends Exception{
        /**
         * Constructs a new exception with the specified detail message.  The
         * cause is not initialized, and may subsequently be initialized by
         * a call to {@link #initCause}.
         *
         * @param message the detail message. The detail message is saved for
         *                later retrieval by the {@link #getMessage()} method.
         */
        public CallableException(String message) {
            super(message);
        }
    }


    public void executorService(){

        //newSingleThreadExecutor
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int index=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(index*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //newFixedThreadPool
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10; i++) {
            int index=i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(index*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //newCachedThreadPool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int index=i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(index*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //newScheduledThreadPool
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            int index=i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(index*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },3,TimeUnit.SECONDS);
        }
    }


}
