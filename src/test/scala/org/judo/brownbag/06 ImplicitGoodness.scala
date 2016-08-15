package org.judo.brownbag

import org.scalatest.FunSpec

/**
  * Created by mark on 15/08/16.
  */

object ImplicitValues {
  implicit val implicitInt = 7
}

class ImplicitDemos extends FunSpec {
  describe("addTo"){
    it("add implicitly scoped int"){
      import ImplicitGoodness._
      implicit val i = 9
      val ten = addTo(1)
      assert(10 === ten)
    }

    it("add implicitly scoped int imported from another scope"){
      import ImplicitGoodness._
      import ImplicitValues._
      val eight = addTo(1)
      assert(8 === eight)
    }
  }
}
