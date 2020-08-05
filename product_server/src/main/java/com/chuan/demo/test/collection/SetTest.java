package com.chuan.demo.test.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 1.自然排序：也就是自然发生的，在创建集合对象时不需要给参数。
 *   注：往TreeSet集合对象中添加元素是，元素必须是可比的（Integer 、Double、Float….），
 *   假如是自定义类型Student，就必须让Student实现Comparable接口；
 * 2.选择器排序：也就是在创建Set集合对象时给他=它一个选择器，也就是构造函数传参（传一个Comparator的子对象），
 *   给集合一个选择排序的标准，具体如何凭借什么选择就看重写Comparator的方法逻辑是怎样实现的
 */
public class SetTest {

    public static void main(String[] args) {
        //1.自然排序：创建集合对象
        TreeSet<Student> tree = new TreeSet<Student>();
        //创建元素
        Student s1 = new Student("霍建华",15);
        Student s2 = new Student("王亚妮",35);
        Student s3 = new Student("张三疯",25);

        //将元素添加到tree中  注：给TreeSet添加对象时，对象必须是可比的（即实现了Comparable接口），要是不是可比的就不知道讲当前对象放在什么位置
        tree.add(s1);
        tree.add(s2);
        tree.add(s3);
        //遍历tree
        Iterator<Student> it = tree.iterator();
        while(it.hasNext()) {
            Student pre = it.next();
            System.out.println("自然排序:"+pre.getName()+"--"+pre.getAge());
        }
//--------------------------------------------------------------------------
        //2.选择器排序
        TreeSet<StudentC>  studentCS = new TreeSet<>(new Comparator<StudentC>() {
            @Override
            public int compare(StudentC o1, StudentC o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        //创建元素
        StudentC sC1 = new StudentC("霍建华",15);
        StudentC sC2 = new StudentC("王亚妮",35);
        StudentC sC3 = new StudentC("张三疯",25);
        studentCS.add(sC1);
        studentCS.add(sC2);
        studentCS.add(sC3);
        //遍历tree
        Iterator<StudentC> itC = studentCS.iterator();
        while(itC.hasNext()) {
            StudentC pre = itC.next();
            System.out.println("选择器排序:"+pre.getName()+"--"+pre.getAge());
        }

    }
}
