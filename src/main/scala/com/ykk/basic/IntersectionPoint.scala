package com.ykk.basic

import scala.collection.JavaConverters._
/**
  * Created by kyakkala on 8/27/2018.
  */
object IntersectionPoint extends App{

  val s1 = "kishore".toSet.asJava
  val s2 = "krishna".toSet.asJava

  println(Stream.from(0, 5).take(6).toList.sliding(2, 2).toList)

}
