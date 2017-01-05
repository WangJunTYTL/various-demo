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


  // def表示定义函数 add 是函数名，
  // 接着这是参数和参数类型，
  // 紧接着是返回值Unit，有时可以不写返回类型，靠编译器推断，返回类型也可以不必谢return
  // 在接着=号后面是方法块，如果方法只有一行 也可以不写大括号
  def add(x: Int, y: Int): Unit = {
    println(x + y)
  }

  def add02(x: Int, y: Int) = println(x + y)

  add02(1, 1);

  // 控制结构 main方法也是scala类的主方法
  def main(args: Array[String]): Unit = {
    add(x, y)
  }


  def add03(x: Int, y: Int) = {
    def add04(x: Int, y: Int, z: Int) = {
      x + y + z
    }

    println(add04(x, y, 1))
  }

  add03(1, 1)

}
