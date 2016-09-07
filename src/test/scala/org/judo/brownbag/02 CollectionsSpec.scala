package org.judo.brownbag

import org.scalatest.FunSpec

/**
  * Created by mark on 14/08/16.
  */

// http://docs.scala-lang.org/resources/images/collections.immutable.png
class CollectionsSpec extends FunSpec{

  describe("List Syntax"){
    it("Lists with different creation syntax but same values should be equal" ){

      val consList = 1 :: 2 :: 3 :: Nil

      val applyList = List(1,2,3)

      assert(consList === applyList)

      val rangeList = 1 to 3 toList

      assert(rangeList === consList)
    }
  }

  describe("Map function"){
    it("should return mapped values"){
      val oneToTen = 1 to 10
      val twoToTwenty = oneToTen.map(i => i * 2)

      assert(twoToTwenty === 2 :: 4 :: 6 :: 8 :: 10 :: 12 :: 14 :: 16 :: 18 :: 20 :: Nil)
    }
  }

  describe("FlatMap function"){
    it("should return flat mapped values"){
      val oneToThree = 1 to 3 toList
      val twoToTwenty = oneToThree.flatMap(i => 1 to i)

      assert(twoToTwenty === 1 :: 1 :: 2 :: 1 :: 2 :: 3 :: Nil)
    }
  }

  describe("Factors of"){
    it("should return factors of 12"){

      import Collections._
      val factorsOfTwelve = factorsOf(12)
      assert(factorsOfTwelve === List(1,2,3,4,6,12))
    }
  }

  //http://www.calculatorsoup.com/calculators/math/commonfactors.php
  describe("Getting common factors with map and flatmap"){
    it("Should return the common factors of 1024 and 8688") {
      import Collections._

      val factorsOf1024 = factorsOf(1024)
      val factorsOf8688 = factorsOf(8688)

      val commonFactors =
        factorsOf1024.flatMap(i =>
          factorsOf8688.map(j => (i, j))).
          filter(tpl => tpl._1 == tpl._2).
          map(tpl => tpl._1)

      assert(commonFactors === List(1, 2, 4, 8, 16))

    }
  }

  describe("Getting common factors with for comprension"){
    it("Should return the common factors of 1024 and 8688") {
      import Collections._

      val factorsOf1024 = factorsOf(1024)
      val factorsOf8688 = factorsOf(8688)

      val commonFactors = for {
        i <- factorsOf1024
        j <- factorsOf8688
        if i == j
      } yield i

      assert(commonFactors === List(1, 2, 4, 8, 16))

    }
  }

}
