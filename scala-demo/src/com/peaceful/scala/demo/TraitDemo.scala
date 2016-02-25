package com.peaceful.scala.demo

/**
  * @author <a href="mailto:wangjuntytl@163.com">WangJun</a> 
  * @version 1.0 16/1/9
  */
class TraitDemo {

  trait Echo{
    def say: Unit ={
      print("hello")
    }
  }

  class Echo2 extends Echo{

    def print(msg:String): Unit ={
      say;
      println(msg)
    }
  }

  def echo: Unit ={
    new Echo2().print("world")
  }
}

object TraitDemo{
  def main(args: Array[String]): Unit = {
    new  TraitDemo().echo
  }
}
