package com.chuan.demo.test;

/**
 * @ClassName ThreadAndVoliate
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/23 16:03
 **/
public class ThreadAndVoliate implements Runnable {

    private int num=100;
    private long numL=0;
    private int numSyn=100;
    private volatile int numVoliate=100;

    private String stringV="haha";
    private volatile String stringVoliate="haha";
//    private volatile boolean flag=true;
    private boolean flag=true;

    public void setStringV(String stringV) {
        this.stringV = stringV;
    }

    public String getStringV() {
        return stringV;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getFlag() {
        return flag;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        /*System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+",num数量："+num);
        num--;
        synchronized (this){
            System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+",numSyn数量："+numSyn);
            numSyn--;
        }*/
//        System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+",numVoliate数量："+numVoliate);
//        numVoliate--;
        System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+getFlag());
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+",num数量："+numL);
            numL++;
        }
//        stringV="huhu";

//        System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName()+",stringVoliate值："+stringVoliate);
//        stringVoliate="huhu";
    }
}
