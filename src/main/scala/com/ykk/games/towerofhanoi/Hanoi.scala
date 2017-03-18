package com.ykk.games.towerofhanoi

import scala.collection.mutable.ListBuffer

/**
  * Created by kishoreyakkala on 13/03/17.
  */
class Hanoi(totalTowers: Int, disks: List[Int], moveFromTower: String, moveToTower: String) {

  val initTowers: Map[String, Tower] = (1 to totalTowers).map { t =>
    s"T${t}" -> (if (s"T${t}" == moveFromTower) Tower(disks, List(disksHashCode(disks))) else Tower(Nil, List(disksHashCode(List.empty[Int]))))
  }.toMap

  def this(totalTowers: Int, disksCount: Int, moveFromTower: Int, moveToTower: Int) = {
    this(totalTowers, (1 to disksCount).toList, s"T$moveFromTower", s"T$moveToTower")
  }

  def getAllSolutions(): List[List[Map[String, Tower]]] = getAllSolutions(0)

  def getAllSolutions(minSteps: Int): List[List[Map[String, Tower]]] = {

    val solutions = ListBuffer.empty[List[Map[String, Tower]]]
    var isOptimalFound = false

    def addSolution(solution: List[Map[String, Tower]]): Unit = {
      if (minSteps != 0 && solution.size <= minSteps) isOptimalFound = true
      solutions += initTowers :: solution.reverse
    }

    def move(towers: Map[String, Tower], curSteps: List[Map[String, Tower]]): Unit = {

      if (isOptimalFound) Unit
      else if (isFinish(towers)) {
        //        println("Found the Solution : ")
        //        curSteps.reverse.foreach(step=> println(step.map(t=> t._1->t._2.disks)))
        //        System.exit(0)
        addSolution(curSteps)
      } else {
        val copyOfTowers: Map[String, Tower] = Map() ++ towers
        copyOfTowers.filter(_._2.disks != Nil).map { t =>
          val (disk :: tailDisks) = t._2.disks
          val newChangedTower = (t._1 -> Tower(tailDisks, disksHashCode(tailDisks) :: t._2.moves))
          towers.foreach { curTower =>
            if (!(t._1 == curTower._1) && (curTower._2.disks == Nil || curTower._2.disks.head >= disk)) {
              val d = disk +: curTower._2.disks
              val newMove: Map[String, Tower] = towers + newChangedTower + (curTower._1 -> Tower(d, disksHashCode(d) :: curTower._2.moves))
              if (!isLooping(newMove.map(t => (t._1, disksHashCode(t._2.disks), copyOfTowers(t._1).moves)).toList))
                move(newMove, newMove :: curSteps)
              //              else println("Stopped as it's looping: " + curSteps)
            }
          }
        }
      }
    }

    move(initTowers, List.empty[Map[String, Tower]])
    solutions.toList
  }

  def isLooping(towerWithHashes: List[(String, String, List[String])]): Boolean = !towerWithHashes.map(t => t._3.contains(t._2)).contains(false)

  def isFinish(towers: Map[String, Tower]): Boolean = towers(moveToTower).disks == disks

  def disksHashCode(disks: List[Int]): String = Integer.toHexString(disks.hashCode())

  def getOptimizeSolution(solutions: List[List[Map[String, Tower]]]): List[Map[String, Tower]] = solutions.map { solution =>
    (solution.size, solution)
  }
    .reduce((x, y) => if (x._1 < y._1) x else y)._2
}