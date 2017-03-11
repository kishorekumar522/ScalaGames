/**
  * Created by kishoreyakkala on 11/03/17.
  */

import java.util

import org.scalatest._

class TCUsingFlatSpec extends FlatSpec with Matchers {

  it should "start before TestCase using FlatSpec" in {
    val x = 0
    println("Hello")
    assert(x == 0)
    assert(x == 1)
  }

  "Stack TestCase" should "pop up the values 1 by 1" in {
    val stack = new util.Stack[Int]()

    stack.push(1)
    stack.push(2)

    stack.pop() should be(2)
    stack.pop() should be(1)
  }
}
