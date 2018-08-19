package com.ykk.codingame

/**
  * Created by kyakkala on 8/19/2018.
  */
object GravityTrambler extends App{

  val trumblerStr = """.................
                      |.................
                      |...##...###..#...
                      |.####..#####.###.
                      |#################""".stripMargin.split(System.lineSeparator()).map(_.toCharArray)
  val count = 1
  val cIdx = trumblerStr.zipWithIndex.map(l => l._2 -> l._1.count(c => c == '#')).toMap
  val (llength, wlength) = (trumblerStr(0).length, trumblerStr.length)
  val trumbledStr = (0 until (if(count % 2 != 0) llength else wlength)).map{j =>
    val antiClockWiseLine = (0 until (if(count % 2 != 0) wlength else llength)).map{i =>
      if(cIdx((if(count % 2 != 0) i else j)) + (if(count % 2 != 0) j else i) >= llength) "#" else "."
    }.mkString
    antiClockWiseLine
  }.mkString
  trumbledStr.
    sliding( if(count % 2 != 0) wlength else llength,
      if(count % 2 != 0) wlength else llength)
    .foreach(println)


}
