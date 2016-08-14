package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */
object HelloWorld extends App{

  override def main(args: Array[String]): Unit = {

    //const string name = GetName(args);
    val name = getName(args)

    if(name != null){
      println(s"Hello, $name!")
    }else{
      println("Hello no-one!")
    }
  }

  def getName(args: Array[String]): String = {
    //String name = null;
    var name: String = null

    if(args.length == 1){
      //name = args[0];
      name = args(0)
    }
    //return name;
    name
  }
}
