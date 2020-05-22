package com.chuan.demo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LambdaTest
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/8 15:01
 **/
public class LambdaTest {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");

        System.out.println("map普通方式遍历:");
        for (String key : map.keySet()) {
            System.out.println("k=" + key + "，v=" + map.get(key));
        }
        System.out.println("map拉姆达表达式遍历:");
        map.forEach((key,vlaue)->{
            System.out.println("key="+key+";value="+vlaue);
        });


        /**
         * ()中括号代表实现类的方法的参数，一个参数的时候，可不需要写中括号，参数可写参数类型，可不写。
         * {}大括号代表方法的实现体，一行代码的时候，可不需要写大括号
         */
        String test = test((a, b) -> {
            System.out.println(a + b);
            return "===" + a + b + "====";
        });

        System.out.println(test);
        String test1 = test((String a,String b) -> {
            System.out.println(a + b);
            return "===" + a + b + "====";
        });
        System.out.println(test1);

        /*test1((a,b)->{
            return a+b;
        });
*/
        testLombda((a) -> {
            System.out.println(a);
        });
        testLombda(a -> System.out.println(a));//一个参数，一行代码，可不需要中括号和大括号
    }

    public static String test(LambdaInterface lambdaInterface){
        System.out.println("lambda使用测试！");
        String a="helllo";
        String b="World!";
        return lambdaInterface.test(a,b);
    }

    private static void test1(LambdaInterface lambdaInterface){
        System.out.println("lambda使用测试！");
        String a="您好";
        String b="世界!";
        String test = lambdaInterface.test(a, b);
        System.out.println(test);
    }
    private static void testLombda(LambdaInterface1 lambdaInterface1){
        System.out.println("lambda使用测试！");
        String a="您好世界!";
        lambdaInterface1.test(a);
    }



}
