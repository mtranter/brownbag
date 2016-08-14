package org.judo.brownbag

import org.scalatest.FunSpec

/**
  * Created by mark on 14/08/16.
  */
class CarSpec extends FunSpec {
  describe("Car"){
    describe("#describe"){
      it("should build correct description") {
        val car = Car(Seq(Make("Vauxhall"), Color("Red"), Year(2010)))
        val desc = car.description
        assert(desc.toLowerCase() === "i am a vauxhall, color: red, made in 2010")
      }
    }
  }
}
