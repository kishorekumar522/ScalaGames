package com.ykk.codingame

/**
  * Created by kyakkala on 8/17/2018.
  */
object BatMan_2D_Binary_Tree extends App{

  var Array(x,y) = Array(4,8)
  var Array(hx,hy) = Array(3,5)
  var (up, left, down, right) = (0 , 0, y-1, x-1)

  while(true){
    val bombdir = readLine.toString
    if(bombdir.contains("U")) down = hy -1
    if(bombdir.contains("D")) up = hy +1
    if(bombdir.contains("L")) right = hx -1
    if(bombdir.contains("R")) left = hx +1
    hx = math.floor((left + right)/2).toInt
    hy = math.floor((up + down)/2).toInt
    println(s"$hx $hy")
  }
}
