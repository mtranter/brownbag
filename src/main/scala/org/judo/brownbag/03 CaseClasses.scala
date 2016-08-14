package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */

object Car{
  def apply(props: Seq[Property[_]]) = new Car(props)
}

class Car(props: Seq[Property[_]]) {

  def description: String = {
    props map buildDrescription _ mkString ", "
  }

  private def buildDrescription(prop: Property[_]) = prop match {
    case Color(c) => s"Color: $c"
    case Year(y) => s"Made in $y"
    case Make(m) => s"I am a $m"
  }

}

abstract sealed class Property[T] {
  val value: T
}

case class Color(value: String) extends Property[String]

case class Year(value: Int) extends Property[Int]

case class Make(value: String) extends Property[String]