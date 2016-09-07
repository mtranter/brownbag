package org.judo.brownbag

import java.util.Date

/**
  * Created by mark on 07/09/16.
  */

object Calculator{

  def add[Number : NumberLike](a: Number, b: Number): Number = implicitly[NumberLike[Number]].add(a,b)
  def subtract[Number : NumberLike](a: Number, b: Number): Number = implicitly[NumberLike[Number]].subtract(a,b)
  def multiply[Number : NumberLike](a: Number, b: Number): Number = implicitly[NumberLike[Number]].multiply(a,b)

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