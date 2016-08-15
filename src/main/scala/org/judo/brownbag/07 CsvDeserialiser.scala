package org.judo.brownbag

/**
  * Created by mark on 15/08/16.
  */
object CsvSerialiser {
  type CW[T] = CsvTokenWriter[T]
  type CR[T] = CsvTokenReader[T]

  def writeCsv[T](graph: T)(implicit cw : CW[T]) = cw.write(graph)
  def readCsv[T](csv: String)(implicit cr : CR[T]) = cr.read(csv)
}

trait CsvTokenReader[T]{
  def read(token: String): T
}

trait CsvTokenWriter[T]{
  def write(valToWrite: T): String
}

trait CsvReaderWriter[T] extends CsvTokenReader[T] with CsvTokenWriter[T]

object DefaultFormats {
  type RW[T] = CsvReaderWriter[T]
  implicit val stringRw = ToFromStringReaderWriter((s: String) => s)
  implicit val intRw = ToFromStringReaderWriter((s: String) => s.toInt)
  implicit val doubleRw = ToFromStringReaderWriter((s: String) => s.toDouble)
  implicit val floatRw = ToFromStringReaderWriter((s: String) => s.toFloat)
  implicit val boolRw = ToFromStringReaderWriter((s: String) => s.toBoolean)

  object ToFromStringReaderWriter{
    def apply[T](fr: String => T) = new ToFromStringReaderWriter[T](fr)
    def apply[T](fr: String => T, fw: T => String = (s:T) => s.toString) = new ToFromStringReaderWriter[T](fr, fw)
  }
  class ToFromStringReaderWriter[T](fr: String => T, fw: T => String = (s: T) => s.toString) extends CsvReaderWriter[T]{
    override def read(token: String): T = fr(token)

    override def write(valToWrite: T): String = fw(valToWrite)
  }
}
