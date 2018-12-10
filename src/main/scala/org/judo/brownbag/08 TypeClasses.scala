package org.judo.brownbag

object Calculator{
  import  NumberLike._
  def add[N: NumberLike](a: N, b: N): N = a + b
  def subtract[N: NumberLike](a: N, b: N): N   = a - b
  def multiply[N: NumberLike](a: N, b: N): N = a * b
}

object NumberLike{
  implicit class NumberOps[T: NumberLike](t: T)(implicit NL: NumberLike[T]){
    def +(other: T) = NL.add(t, other)
    def *(other: T) = NL.multiply(t, other)
    def -(other: T) = NL.subtract(t, other)
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