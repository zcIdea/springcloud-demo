package com.chuan.demo.test;

/**
 * volatile的使用示例
 * 线程之间相互配合，完成某项工作，
 * 比如：一个线程修改了一个对象的值，而另一个线程感知到了变化，
 * 然后进行相应的操作，整个过程开始于一个线程，而最终执行又是另一个线程。
 * 前者是生产者，后者就是消费者，这种模式隔离了“做什么”（what）和“怎么做”（How），
 * 简单的办法是让消费者线程不断地循环检查变量是否符合预期在while循环中设置不满足的条件，
 * 如果条件满足则退出while循环，从而完成消费者的工作
 * @ClassName VolitileCom
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/6/5 16:11
 **/
public class VolatileCom {

    private static VolatileCom volatileCom=new VolatileCom();

    /**
     * volatile修饰的变量
     */
    public volatile boolean flag=true;

    private VolatileCom(){}

    public static VolatileCom getVolatileCom(){
        return volatileCom;
    }

    public static void main(String[] args) {
        VolatileCom volatileCom = VolatileCom.getVolatileCom();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"；volatileCom.flag="+volatileCom.flag);
                while (volatileCom.flag){
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"；volatileCom.flag="+volatileCom.flag);
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
                System.out.println(Thread.currentThread().getName()+"；volatileCom.flag="+volatileCom.flag);
                volatileCom.flag=false;
                System.out.println(Thread.currentThread().getName()+"；volatileCom.flag="+volatileCom.flag);
            }
        }).start();
    }


}
