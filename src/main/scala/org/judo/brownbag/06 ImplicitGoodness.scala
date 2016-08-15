package org.judo.brownbag

/**
  * Created by mark on 15/08/16.
  */
object ImplicitGoodness {

  def addTo(numToAdd: Int)(implicit addToWhat: Int): Int = {
    numToAdd + addToWhat
  }
}
