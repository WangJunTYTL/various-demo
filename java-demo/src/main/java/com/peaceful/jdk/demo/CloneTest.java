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
        Util.report(student);
        Util.report(student1);
        Util.report(student.id+student.getName());
        Util.report(student.id+student1.getName());

        String[] a = {"a","b","c"};
        Util.report(a);
        String[] b = a.clone();
        Util.report(b);
        b[1]="f";
        Util.report(a[1]);
        Util.report(b[1]);

        Student[] students = {student};
        Student[] students1 = students.clone();
        Util.report(students);
        Util.report(students1);
        students1[0].setName("ww");
        Util.report(students[0].getName());//只是把数组每个元素的内存地址复制过去
        Util.report(students1[0].getName());

    }
}
