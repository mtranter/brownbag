package org.judo.brownbag

/**
  * Created by mark on 15/08/16.
  */
object CsvDeserialiser {
  type CR[T] = CsvTokenReader[T]

  def readCsv[T](csv: String)(implicit cr : CR[T]) = cr.read(csv)
}

trait CsvTokenReader[T]{
  def read(token: String): T
}


trait DefaultFormats {

  type CR[T] = CsvTokenReader[T]

  implicit val stringRw = ToFromStringReader((s: String) => s)
  implicit val intRw = ToFromStringReader((s: String) => s.toInt)
  implicit val doubleRw = ToFromStringReader((s: String) => s.toDouble)
  implicit val floatRw = ToFromStringReader((s: String) => s.toFloat)
  implicit val boolRw = ToFromStringReader((s: String) => s.toBoolean)

  object ToFromStringReader{
    def apply[T](fr: String => T) = new ToFromStringReader[T](fr)
  }

  class ToFromStringReader[T](fr: String => T, fw: T => String = (s: T) => s.toString) extends CsvTokenReader[T]{
    override def read(token: String): T = fr(token)
  }

  implicit def format1[T, P1 : CR](ctr: P1 => T): CsvTokenReader[T] = new CsvTokenReader[T] {
    override def read(token: String): T = {
      val p1Reader = implicitly[CsvTokenReader[P1]]
      val p1 = p1Reader.read(token)
      ctr(p1)
    }
  }

  implicit def format2[T, P1 : CR, P2 : CR](ctr: (P1, P2) => T): CsvTokenReader[T] = new CsvTokenReader[T] {
    override def read(token: String): T =  {
      val strings = token split "," map { s => s.trim}
      val p1Reader = implicitly[CsvTokenReader[P1]]
      val p1 = p1Reader.read(strings(0))
      val p2Reader = implicitly[CsvTokenReader[P2]]
      val p2 = p2Reader.read(strings(1))
      ctr(p1, p2)
    }
  }

  implicit def format3[T, P1 : CR, P2 : CR, P3 : CR](ctr: (P1, P2, P3) => T): CsvTokenReader[T] = new CsvTokenReader[T] {
    override def read(token: String): T =  {
      val strings = token split "," map { s => s.trim}
      val p1Reader = implicitly[CsvTokenReader[P1]]
      val p1 = p1Reader.read(strings(0))
      val p2Reader = implicitly[CsvTokenReader[P2]]
      val p2 = p2Reader.read(strings(1))
      val p3Reader = implicitly[CsvTokenReader[P3]]
      val p3 = p3Reader.read(strings(2))
      ctr(p1, p2, p3)
    }
  }

  implicit def format4[T, P1 : CR, P2 : CR, P3 : CR, P4 : CR](ctr: (P1, P2, P3, P4) => T): CsvTokenReader[T] = new CsvTokenReader[T] {
    override def read(token: String): T =  {
      val strings = token split "," map { s => s.trim}
      val p1Reader = implicitly[CsvTokenReader[P1]]
      val p1 = p1Reader.read(strings(0))
      val p2Reader = implicitly[CsvTokenReader[P2]]
      val p2 = p2Reader.read(strings(1))
      val p3Reader = implicitly[CsvTokenReader[P3]]
      val p3 = p3Reader.read(strings(2))
      val p4Reader = implicitly[CsvTokenReader[P4]]
      val p4 = p4Reader.read(strings(3))

      ctr(p1, p2, p3, p4)
    }
  }
}

object DefaultFormats extends DefaultFormats