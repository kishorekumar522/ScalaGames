package com.ykk.games.snakeladder

import scala.annotation.tailrec
import scala.util.Random

/**
  * Created by kishoreyakkala on 10/03/17.
  */
class SnakeNLadderImpl(snakes: Map[Int, Int], ladders: Map[Int, Int], players: List[String], boardSize: Int, maxChance: Int) {

  val snl = new SnakeNLadder(snakes, ladders, boardSize)
  //  create Circular Loop of players
  val looper = Iterator.continually(players.map(Player(_))).flatten

  def startSnakeLader() = {

    validateBoard
    snl.start(players)
  }

  def validateBoard() = {
    if (!(boardSize >= snakes.keys.max && 0 < snakes.keys.min)) throw SnakeNLadderException("snakes or ladders inputs are wrong")
  }

  @tailrec
  final def rollDice: Int = Random.nextInt(maxChance) match {
    case 0 => rollDice
    case x: Int => x
  }
}
