package com.chuan.demo.thread;

/**
 * 使用ThreadLocal不能继承父线程的ThreadLocal的内容，而使用InheritableThreadLocal时可以做到的，这就可以很好的在父子线程之间传递数据了
 * 将父线程的ThreadLocalMap复制到自己的ThreadLocalMap里面来，这样我们就可以使用InheritableThreadLocal访问到父线程中的变量了
 * @ClassName InheritableThreadLocalTest
 * @Description: TODO
 * @Author: 张川
 * @Date 2020/6/2 17:22
 **/
public class InheritableThreadLocalTest {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<Integer> inheritableThreadLocal =
            new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        integerThreadLocal.set(1001); // father
        inheritableThreadLocal.set(1002); // father

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"
                + integerThreadLocal.get() + "/"
                + inheritableThreadLocal.get())).start();

    }

}
