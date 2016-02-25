package com.peaceful.scala.demo

/**
  * @author <a href="mailto:wangjuntytl@163.com">WangJun</a> 
  * @version 1.0 16/1/8
  */
object ListDemo {

  def main(args: Array[String]) {
    val oneTwoThree = List(1, 2, 3);
    println(oneTwoThree)
    var oneTwo = List(1, 2)
    var threeFour = List(3, 4)
    var oneTwoThreeFour = oneTwo ::: threeFour
    println(oneTwoThreeFour)
  }

}
