package com.ykk.play

import com.ykk.games.towerofhanoi.{Hanoi, Tower}

/**
  * Created by kishoreyakkala on 12/03/17.
  */

object HanoiMain {

  def main(args: Array[String]) {


    val totalTowers = 3
    val disksCount = 4
    val moveFromTower = 1
    val moveToTower = 3
    val hanoiGame = new Hanoi(totalTowers, disksCount, moveFromTower, moveToTower)

    //    If you pass argument to getAllSolutions, it try to get the optimal solution steps <= input without figuring all the solutions
    //    you can verify just by toggling the below steps(22 && 23)
    //    val solutions:List[List[Map[String, Tower]]] = hanoiGame.getAllSolutions(15)
    val solutions: List[List[Map[String, Tower]]] = hanoiGame.getAllSolutions()
    getSummary(hanoiGame, solutions)
  }

  def getSummary(hanoi: Hanoi, solutions: List[List[Map[String, Tower]]]): Unit = {
    println("Total Number of Solutions : " + solutions.size)
    println("Optimal Moves : " + (hanoi.getOptimizeSolution(solutions).size - 1))
    hanoi.getOptimizeSolution(solutions).foreach(step => println(step.map(t => t._1 -> t._2.disks)))
  }


}