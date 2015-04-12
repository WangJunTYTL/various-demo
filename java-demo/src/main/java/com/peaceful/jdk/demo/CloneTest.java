package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;
import com.peaceful.jdk.demo.impl.Student;

/**
 * Created by wangjun on 14/11/29.
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setName("wj");
        Student student1 = student.clone();
        student1.eleA.setName("123"); // 引用类型只会复制引用地址，不会clone引用对象
        //clone后的类地址是不同的
        Util.report(student);
        Util.report(student1);
        Util.report(student.eleA.name);
        Util.report(student1.eleA.name);
        //field value 都是一样的
        Util.report(student.id+student.getName());
        Util.report(student.id+student1.getName());

        String[] a = {"a","b","c"};
        Util.report(a);
        String[] b = a.clone();
        Util.report(b);
        b[1]="f";
        Util.report(a[1]);
        Util.report(b[1]);

        //student object must implements  interface {@code Cloneable},then a {@code CloneNotSupportedException} is thrown.
       //数组的复制只会返回元素的引用类型或primitive type
        Student[] students = {student};
        Student[] students1 = students.clone();
        Util.report(students);
        Util.report(students1);
        students1[0].setName("ww");
        Util.report(students[0].getName());//只是把数组每个元素的内存地址复制过去，这个时候便显现了克隆的深度
        Util.report(students1[0].getName());
    }
}
