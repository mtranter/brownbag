package org.judo.brownbag

import java.util.Date

/**
  * Created by mark on 07/09/16.
  */

object Calculator{
  def add[Number](a: Number, b: Number)(implicit nl: NumberLike[Number]): Number = nl.add(a,b)
  def subtract[Number](a: Number, b: Number)(implicit nl: NumberLike[Number]): Number = nl.subtract(a,b)
  def multiply[Number](a: Number, b: Number)(implicit nl: NumberLike[Number]): Number = nl.multiply(a,b)
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