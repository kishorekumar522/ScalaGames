package com.ykk.games.snakeladder

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by kishoreyakkala on 10/03/17.
  */
case class Player(name: String)

case class SnakeNLadderException(msg: String) extends Exception

case class SnakeNLadder(snakes: Map[Int, Int], ladders: Map[Int, Int], boardSize: Int) extends SnakeLadderBoard {

  //  val log = LoggerFactory.getLogger(this.getClass)

  val startPosition = 1
  var startTime = System.currentTimeMillis()
  var playerLocation = mutable.Map[Player, Int]()
  var gameMoves = mutable.Map[Player, ArrayBuffer[(MoveType, Int, Int)]]()

  override def move(player: Player, chance: Int): (Player, Boolean) = {

    val curLocation = playerLocation(player)
    val rslt = curLocation + chance match {
      case newLocation if snakes.get(newLocation) != None => (Snake, snakes(newLocation), false)
      case newLocation if ladders.get(newLocation) != None => (Ladder, ladders(newLocation), true)
      case newLocation if isValid(newLocation) => (NormalMove, newLocation, false)
      case newLocation if !isValid(newLocation) => (NormalMove, curLocation, true)
    }

    gameMoves(player).+=:(rslt._1, curLocation, rslt._2)
    playerLocation(player) = rslt._2

    (player, rslt._3)

  }

  def isValid(position: Int) = if (position > boardSize) false else true

  override def start(players: List[String]): List[Player] = {
    startTime = System.currentTimeMillis()
    val tPlayers: List[Player] = players.map(Player(_))
    addAllPlayers(tPlayers)
    tPlayers
  }

  def addAllPlayers(members: List[Player]): Unit = {
    playerLocation = mutable.Map(members.map(_ -> startPosition).toSeq: _*)
    gameMoves = mutable.Map(members.map(_ -> ArrayBuffer.empty[(MoveType, Int, Int)]): _*)
  }

  override def isGameOver(): Boolean = playerLocation.values.max == boardSize

  override def stop(winner: Player): List[String] = {
    List(s"Winner of the Game : ${winner.name}",
      s"Total Players Played : ${playerLocation.map(_._1.name).mkString(" , ")}",
      s"Time Took : ${System.currentTimeMillis() - startTime} mSec & total Moves : ${gameMoves.map(_._2.length).sum}"
    ) ::: gameMoves(winner).toArray.reverse.map(m => s"${m._1.getClass.getSimpleName.replace("$", "")}: ${m._2} -> ${m._3}").toList
  }


}

