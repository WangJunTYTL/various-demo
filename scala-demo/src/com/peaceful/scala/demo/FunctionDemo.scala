package com.peaceful.scala.demo

/**
  * Created by wangjun on 2016/10/26.
  */
object FunctionDemo {

  def main(args: Array[String]): Unit = {
    var arr =  Array("1","2","3");
    // 这里直接传入函数名作为参数
    arr.foreach(println);
  }

  def formatArgs(args: Array[String]) = args.mkString("-");

  println(formatArgs(Array("1","2","3")));

}
