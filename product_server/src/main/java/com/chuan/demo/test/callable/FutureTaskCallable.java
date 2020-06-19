package com.chuan.demo.test.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTaskCallable
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/8 15:43
 **/
public class FutureTaskCallable {

    static class CallableTest implements Callable<String>{

        private String name;

        public CallableTest() {
        }

        public CallableTest(String name) {
            this.name=name;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            System.out.println(name+"；Callable子线程开始");
            return name+"；hello callable!";
        }
    }

    public static void main(String[] args) {

        CallableTest callableTest=new CallableTest("1.hauhuah");
        FutureTask<String> futureTask=new FutureTask<>(callableTest);
        new Thread(futureTask).start();

        CallableTest callableTest2=new CallableTest("2.hahah");
        FutureTask<String> futureTask2=new FutureTask<>(callableTest2);
        new Thread(futureTask2).start();

        try {
            Thread.sleep(1000);
            String s = futureTask.get();
            String s2 = futureTask2.get();
            System.out.println(s);
            System.out.println(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
