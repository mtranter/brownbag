package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */

// http://docs.scala-lang.org/resources/images/collections.immutable.png
object Collections {

  def factorsOf(n: Int) = 1 to n filter {i => n % i == 0}

}
