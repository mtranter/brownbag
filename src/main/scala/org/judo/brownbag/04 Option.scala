package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */
abstract sealed class Maybe[T] {

  val value: T

}

//case class Yes[T](val T)
