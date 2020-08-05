package com.chuan.demo.test.collection;

/**
 *
 * @author zc124
 */
public class Student implements Comparable<Student>{

    private String name;
    private int age;
    public Student() {
        super();
    }
    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        //按年龄排序
        return this.age-o.age;
    }
}
