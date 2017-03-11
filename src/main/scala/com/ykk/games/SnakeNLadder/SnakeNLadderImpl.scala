package com.ykk.games.SnakeNLadder

import scala.annotation.tailrec
import scala.util.Random

/**
  * Created by kishoreyakkala on 10/03/17.
  */
class SnakeNLadderImpl(snakes: Map[Int, Int], ladders: Map[Int, Int], players: List[String], boardSize: Int, maxChance: Int) {

  val snl = new SnakeNLadder(snakes, ladders, boardSize)
  val looper = Iterator.continually(players.map(Player(_))).flatten

  def checkInit() = {
    if ((boardSize >= snakes.keys.max && 0 < snakes.keys.min)) throw SnakeNLadderException("snakes or ladders inputs are wrong")
  }

  @tailrec
  final def rollOver(player: Player): Player = {

    val roll = rollDice(maxChance)
    val playerMove = snl.move(player, roll)

    playerMove match {
      case pm if snl.isGameOver() => player
      case pm if pm._2 == true && !snl.isGameOver() => rollOver(pm._1)
      case pm if pm._2 == false && !snl.isGameOver() => rollOver(looper.next)
    }

  }


  def gameOn = {

    val startPlayer = looper.next()
    snl.start(players)
    snl.stop(rollOver(startPlayer))

  }

  @tailrec
  final def rollDice(max: Int): Int = Random.nextInt(max) match {
    case 0 => rollDice(max)
    case x: Int => x
  }
}
