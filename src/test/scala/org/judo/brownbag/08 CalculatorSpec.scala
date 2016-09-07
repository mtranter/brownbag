package org.judo.brownbag

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by mark on 07/09/16.
  */
class CalculatorSpec extends FunSpec with Matchers{
  describe("Calculator"){
    describe("Add"){
      it("Should return the correct integer addition"){
        implicit val numberlike = NumberLikeInt
        val retval = Calculator.add(3,4)
        retval should be(7)
      }

      it("will shit itself for decimals"){
        implicit val numberlike = NumberLikeDouble
        val retval = Calculator.add(4d, 5d)
        retval should be(9d)
      }

    }
  }
}


