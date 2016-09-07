package org.judo.brownbag

import org.scalatest.{FunSpec, Matchers}
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

/**
  * Created by mark on 07/09/16.
  */
class FuncConversions extends FunSpec with Matchers {
  describe("FuncConversions"){

    it("Is be fugly doing doing manual interface implementation"){

      val elems = List(1,2,3,4,5).asJava
      val jEnum = new JEnumerable[Int](elems)

      val sumOfDoubledEvens = jEnum.where(new Filter[Int] {
        override def filter(i: Int): Boolean = i % 2 == 0
      }).select(new Mapper[Int, Int] {
        override def map(i: Int): Int  = i * 2
      }).aggregate(0, new Aggregator[Int, Int] {
        override def aggregate(target: Int, source: Int): Int = target + source
      })

    }

    it("Is far nicer using implicit conversions!"){
      import ImplicitFuncs._
      val elems = List(1,2,3,4,5).asJava
      val jEnum = new JEnumerable[Int](elems)
      val sumOfDoubledEvens = jEnum.
        where((i: Int) => i % 2 == 0).
        select((i: Int) => i * 2).
        aggregate(0,(acc: Int, v: Int) => acc + v)

    }
  }
}
