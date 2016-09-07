package org.judo.brownbag

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by mark on 07/09/16.
  */
class CalculatorSpec extends FunSpec with Matchers{
  describe("Calculator"){
    describe("Add"){
      it("Should return the correct integer addition"){
        val retval = Calculator.add(3,4)
        retval should be(7)
      }
    }
  }
}
