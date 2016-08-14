package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */
object HelloWorld extends App{

  override def main(args: Array[String]): Unit = getName(args) match {
    case Yes(name) => println(s"Hello, $name!")
    case No => println("Hello World")
  }

  def getName(args: Array[String]): Maybe[String] = if(args.length == 1) Yes(args(0)) else No

}
