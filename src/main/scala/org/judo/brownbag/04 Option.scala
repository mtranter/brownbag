package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */
abstract sealed class Maybe[T] {

  val value: T

}

case class Yes[T](value: T) extends Maybe[T]

case object No extends Maybe[Nothing] {
  override val value: Nothing = throw new Exception("Invalid access of 'value' in No class")
}
