package org.judo.brownbag

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by mark on 15/08/16.
  */
class CsvWriterSpec extends FlatSpec with Matchers {


  "The CsvDeserializer" should "correctly deserialize a string value given an explicit reader" in {
    val deserialzedString = CsvDeserialiser.readCsv("123")(new CsvTokenReader[String] {
      override def read(token: String): String = token
    })
    deserialzedString should be("123")
  }


  it should "correctly deserialize a int value given an explicit reader" in {
    val deserialzedString = CsvDeserialiser.readCsv("123")(new CsvTokenReader[Int] {
      override def read(token: String): Int = token.toInt
    })
    deserialzedString should be(123)
  }


  it should "correctly deserialize a int value given an imported implicit reader" in {
    import DefaultFormats._
    val deserialzedString = CsvDeserialiser.readCsv[Int]("123")
    deserialzedString should be(123)
  }

  object MyFormats extends DefaultFormats {
    implicit val nameFormat = format1(Name)
    implicit val fullNameFmt = format2(FullName)
    implicit val personFormat = format3(Person)
    implicit val weightedSexedPersonFormat = format4(WeightedSexedPerson)
  }

  it should "correctly deserialize a case class arity 1 given imported implicit formats" in {
    import MyFormats._
    val deserialzedString = CsvDeserialiser.readCsv[Name]("judo")
    deserialzedString should be(Name("judo"))
  }

  it should "correctly deserialize a case class arity 2 given imported implicit formats" in {
    import MyFormats._
    val deserialzedString = CsvDeserialiser.readCsv[FullName]("judo, payments")
    deserialzedString should be(FullName("judo","payments"))
  }
  it should "correctly deserialize a case class arity 3 given imported implicit formats" in {
    import MyFormats._
    val deserialzedString = CsvDeserialiser.readCsv[Person]("1, judo, payments")
    deserialzedString should be(Person(1, "judo","payments"))
  }
  it should "correctly deserialize a case class arity 4 given imported implicit formats" in {
    import MyFormats._
    val deserialzedString = CsvDeserialiser.readCsv[WeightedSexedPerson]("1, judo, 93.254, true")
    deserialzedString should be(WeightedSexedPerson(1, "judo", 93.254, true))
  }
}

case class Name(name: String)
case class FullName(firstName: String, lastName: String)
case class Person(id: Int,firstName: String, lastName: String)
case class WeightedSexedPerson(id: Int, name: String, weight: Double, isMale: Boolean)
