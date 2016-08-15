package org.judo.brownbag

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by mark on 15/08/16.
  */
class CsvWriterSpec extends FlatSpec with Matchers {
  "The CsvDeserializer" should "correctly serialize a string value given an explicit writer" in {
    val csvStr = CsvSerialiser.writeCsv("123")(new CsvTokenWriter[String] {
      override def write(valToWrite: String): String = valToWrite
    })
    csvStr should be("123")
  }

  it should "correctly deserialize a string value given an explicit reader" in {
    val deserialzedString = CsvSerialiser.readCsv("123")(new CsvTokenReader[String] {
      override def read(token: String): String = token
    })
    deserialzedString should be("123")
  }

  it should "correctly serialize a int value given an explicit writer" in {
    val csvStr = CsvSerialiser.writeCsv(123)(new CsvTokenWriter[Int] {
      override def write(valToWrite: Int): String = valToWrite.toString
    })
    csvStr should be("123")
  }

  it should "correctly deserialize a int value given an explicit reader" in {
    val deserialzedString = CsvSerialiser.readCsv("123")(new CsvTokenReader[Int] {
      override def read(token: String): Int = token.toInt
    })
    deserialzedString should be(123)
  }

  it should "correctly serialize a int value given an imported implicit writer" in {
    import DefaultFormats._
    val csvStr = CsvSerialiser.writeCsv(123)
    csvStr should be("123")
  }

  it should "correctly deserialize a int value given an imported implicit reader" in {
    import DefaultFormats._
    val deserialzedString = CsvSerialiser.readCsv[Int]("123")
    deserialzedString should be(123)
  }
}
