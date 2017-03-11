package com.ykk.games.SnakeNLadder

/**
  * Created by kishoreyakkala on 10/03/17.
  */


trait MoveType

object Snake extends MoveType

object Ladder extends MoveType

object NormalMove extends MoveType

trait GameKeeper {

  val snakes: Map[Int, Int]
  val ladders: Map[Int, Int]
  val boardSize: Int


  def move(player: Player, chance: Int): (Player, Boolean)

  def start(players: List[String]): List[Player]

  def stop(winner: Player)

  def isGameOver(): Boolean

}
