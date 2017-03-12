package com.ykk.play

import com.ykk.games.snakeladder.{Player, SnakeNLadderImpl}

import scala.annotation.tailrec

/**
  * Created by kishoreyakkala on 10/03/17.
  */
object PlaySnakeLadderAutomatic {

  def main(args: Array[String]) {

    val snakes = Map(89 -> 40, 59 -> 32)
    val ladders = Map(39 -> 43, 23 -> 42)
    val boardSize = 100
    val maxChance = 6
    val players = List("Kishore", "Goku", "Beerus", "Vegeta")

    val game = new SnakeNLadderImpl(snakes, ladders, players, boardSize, maxChance)
    gameOn(game)

  }

  def gameOn(game: SnakeNLadderImpl) = {

    val startPlayer = game.looper.next()

    @tailrec
    def rollOver(player: Player): Player = {

      val roll = game.rollDice
      val playerMove = game.snl.move(player, roll)

      playerMove match {
        case pm if game.snl.isGameOver() => player
        case pm if pm._2 == true && !game.snl.isGameOver() => rollOver(pm._1)
        case pm if pm._2 == false && !game.snl.isGameOver() => rollOver(game.looper.next)
      }
    }

    game.startSnakeLader
    val rslt = game.snl.stop(rollOver(startPlayer))


    val summary = List(s"Winner of the Game : ${rslt._1.name}",
      s"Total Players Played : ${rslt._3.keys.map(_.name).mkString(" , ")}",
      s"Time Took : ${rslt._2} mSec & total Moves : ${rslt._3.map(_._2.length).sum}"
    ) ::: rslt._3(rslt._1).toArray.reverse.map(m => s"${m._1.getClass.getSimpleName.replace("$", "")}: ${m._2} -> ${m._3}").toList

    summary.foreach(println)
  }


}