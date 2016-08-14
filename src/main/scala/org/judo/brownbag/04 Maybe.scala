package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */
abstract sealed class Maybe[+T] {

  def value: T

  def hasValue = this match{
    case Yes(_) => true
    case No => false
  }

  def map[S](f: T => S) = this match {
    case Yes(v) => Yes(f(v))
    case No => No
  }

  def flatMap[S](f: T => Maybe[S]) = this match {
    case Yes(v) => f(v)
    case No => No
  }

  def filter(f: T => Boolean) = this match {
    case Yes(v) => f(v)
    case No => false
  }

}

case class Yes[T](value: T) extends Maybe[T]

case object No extends Maybe[Nothing] {
  override def value: Nothing = throw new Exception("Invalid access of 'value' in No class")
}
