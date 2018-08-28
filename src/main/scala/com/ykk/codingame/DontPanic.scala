

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object DontPanic extends App {
  val Array(nbfloors, width, nbrounds, exitfloor, exitpos, nbtotalclones, nbadditionalelevators, nbelevators) = for (i <- readLine split " ") yield i.toInt
  val elevators = (0 until nbelevators).map { i =>
    val Array(elevatorfloor, elevatorpos) = for (i <- readLine split " ") yield i.toInt
    (elevatorfloor -> elevatorpos)
  }.toMap + (exitfloor -> exitpos)
  Console.err.println(nbfloors, width, nbrounds, exitfloor, exitpos, nbtotalclones, nbadditionalelevators, nbelevators)
  Console.err.println(elevators)
  // game loop
  while (true) {

    val Array(_clonefloor, _clonepos, direction) = readLine.split(" ")
    val clonefloor = _clonefloor.toInt
    val clonepos = _clonepos.toInt
    val curDirection = elevators.get(clonefloor).map{x => if((x-clonepos) == 0 || ((x-clonepos) < 0 && direction == "LEFT") || ((x-clonepos) > 0 && direction == "RIGHT")) "WAIT" else "BLOCK"}.getOrElse("BLOCK")
    println(curDirection)
  }
}