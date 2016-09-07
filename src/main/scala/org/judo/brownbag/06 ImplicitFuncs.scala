package org.judo.brownbag

/**
  * Created by mark on 07/09/16.
  */
object ImplicitFuncs {

  implicit def funcToFilter[T](f: T => Boolean): Filter[T] = new Filter[T] {
    override def filter(t: T): Boolean = f(t)
  }

  implicit def funcToAgg[T,Target](f: (Target, T) => Target): Aggregator[T, Target] = new Aggregator[T, Target] {
    override def aggregate(target: Target, source: T): Target = f(target,source)
  }

  implicit def funcToMapper[From, To](f: From => To): Mapper[From, To] = new Mapper[From, To] {
    override def map(from: From): To = f(from)
  }

}
