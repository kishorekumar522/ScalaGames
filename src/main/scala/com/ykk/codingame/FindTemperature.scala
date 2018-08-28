import scala.util._

object FindTemperature extends App {
  val n = readInt
  val allTemps = Try(readLine.split(" ").map(_.toInt).toList).getOrElse(Nil)
  val (pn, nn) = allTemps.partition(_ > 0)
  val result = (Try(pn.min), Try(nn.max)) match{
    case (Success(x), Success(y)) => math.max(x,y)
    case (Success(x), _) => x
    case (_, Success(y)) => y
    case _ => 0
  }
  println(s"$result")
}