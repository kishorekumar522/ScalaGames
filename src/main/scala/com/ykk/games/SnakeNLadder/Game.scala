package com.ykk.games.SnakeNLadder

/**
  * Created by kishoreyakkala on 10/03/17.
  */
object Game {

  def main(args: Array[String]) {

    val snakes = Map(89 -> 40, 59 -> 32)
    val ladders = Map(39 -> 43, 23 -> 42)
    val boardSize = 100
    val maxChance = 6
    val players = List("kishore", "wei", "dragon", "jack")

    val game = new SnakeNLadderImpl(snakes, ladders, players, boardSize, maxChance)
    game.gameOn


  }


}