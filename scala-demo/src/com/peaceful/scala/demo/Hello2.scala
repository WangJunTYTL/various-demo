package com.peaceful.scala.demo

/**
  * @author <a href="mailto:wangjuntytl@163.com">WangJun</a> 
  * @version 1.0 16/1/8
  */
object Hello2 {

  def main(args: Array[String]) {

    // 实现Java中的Runnable接口
    val test: Thread = new Thread(new Runnable {
      override def run(): Unit = {
        println("hello world")
      }
    });
    test.start();
  }

}
