/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object AeonGame extends App {
  val speed = readInt
  val lightcount = readInt
  val trafficLights = (0 until lightcount).map{_ =>
    val Array(distance, duration) = for(i <- readLine split " ") yield i.toInt
    (distance, duration)
  }.toList
  Console.err.println(speed, trafficLights)
  println(calcSpeed(speed, trafficLights))

  def calcSpeed(maxSpeed:Int, lights:List[(Int, Int)]) = {
    val maxDistance = lights.map(_._1).max*2
    (maxSpeed until 0 by -1).toStream.find{v =>
      val mps = v * 5.0/18

      def crossedInTime(light:(Int, Int)):Boolean = {
        var isTrafficLightOn = 0
        val validRanges = Stream.from(0,light._2).takeWhile(x => (x-1)*mps<=maxDistance).toList.sliding(2,1).filter{_ => isTrafficLightOn += 1; isTrafficLightOn%2!=0}
          .map{case List(x,y) => (math.round(x*mps*100)/100.0,math.round(y*mps)/100.0)}.toList
        val iResult = validRanges.exists{case (mi, mx) => mi <= light._1 && mx > light._1}
        iResult
      }
      val result = lights.map(crossedInTime).reduce(_&&_)
      result
    }.getOrElse(0)
  }

  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

}