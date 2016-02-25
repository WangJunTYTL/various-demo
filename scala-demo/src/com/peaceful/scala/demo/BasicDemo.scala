package com.peaceful.scala.demo

/**
  *
  * object修饰是单例对象
  * Class 修饰是定义类
  *
  * @author <a href="mailto:wangjuntytl@163.com">WangJun</a> 
  * @version 1.0 16/1/8
  */
object BasicDemo {

  var x = 5;
  // 不可变 变量
  val y = 6;

  // 定义数组
  val greet: Array[String] = new Array[String](3);
  val greet02: Array[String] = Array("hello", " ", "world");


  // 可以直接在这里写语句块
  greet(0) = "hello"
  greet(1) = " "
  greet(2) = "world"
  for (i <- 0 to 2)
    print(greet(i))
  println("")

  // 可以直接调用Java对象
  Hello.say("hello world!")


  // 定义函数
  def add(x: Int, y: Int): Unit = {
    print(x + y)
  }


  // 控制结构
  def main(args: Array[String]): Unit = {
    add(x, y)
  }

}
