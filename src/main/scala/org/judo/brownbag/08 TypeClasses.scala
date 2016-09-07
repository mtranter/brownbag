package org.judo.brownbag

import java.util.Date

/**
  * Created by mark on 07/09/16.
  */

object Calculator{
  import  NumberLike._
  def add[Number : NumberLike](a: Number, b: Number): Number = a + b
  def subtract[Number : NumberLike](a: Number, b: Number): Number = a - b
  def multiply[Number : NumberLike](a: Number, b: Number): Number = a * b

}

object NumberLike{

  implicit class NumberOps[T: NumberLike](t: T){
    val numlike = implicitly[NumberLike[T]]
    def +(other: T) = numlike.add(t, other)
    def *(other: T) = numlike.multiply(t, other)
    def -(other: T) = numlike.subtract(t, other)
  }
}


trait NumberLike[T]{
  def add(a: T, b: T): T
  def subtract(a: T, b: T): T
  def multiply(a: T, b: T): T
}

object NumberLikeInt extends NumberLike[Int]{
  override def add(a: Int, b: Int): Int = a + b

  override def multiply(a: Int, b: Int): Int = a * b

  override def subtract(a: Int, b: Int): Int = a - b
}

object NumberLikeDouble extends NumberLike[Double]{
  override def add(a: Double, b: Double): Double = a + b

  override def multiply(a: Double, b: Double): Double = a * b

  override def subtract(a: Double, b: Double): Double = a - b
}