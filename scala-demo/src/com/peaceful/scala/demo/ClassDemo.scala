package com.peaceful.scala.demo

/**
  * @author <a href="mailto:wangjuntytl@163.com">WangJun</a> 
  * @version 1.0 16/1/9
  */
class ClassDemo {


}

abstract class Element {

  //  定义一个抽象方法
  def contents: Array[String]

  val height: Int = contents.length

  val width: Int = if (height == 0) 0 else contents(0).length
}


class ArrayElement(conts: Array[String]) extends Element {

  //  实现一个抽象方法
  override def contents: Array[String] = conts
}

object Setup {

  var ele = new Element {
    override def contents: Array[String] = {
      Array("1", "2", "3")
    }
  }

  println(ele.contents(1))

  var ele2 = new ArrayElement(Array("hello","world"));

  println(ele2.contents(1))

  def main(args: Array[String]) {
    // 方法也可以这样调用,省去.
   print( ele contents 1)
  }

}
