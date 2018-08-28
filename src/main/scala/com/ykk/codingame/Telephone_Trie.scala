import java.util

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.HashMap
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
  val n = readInt
  val telephones = (0 until n).map(_ => readLine).toList
  Console.err.println(telephones)
  // Write an action using println
  // To debug: Console.err.println("Debug messages...")

  val trie = Trie('\0')
  // The number of elements (referencing a number) stored in the structure.
  println("number")

  def addElement(phoneNumber:List[Char], tTrie:Trie):Unit = {
    val h::t = phoneNumber
    val curTrie = tTrie.nextElement.get(h).getOrElse(Trie(h))
    if(!phoneNumber.isEmpty) addElement(t, curTrie)
  }

  def count(trie:Trie):Long = {
    val queue:util.Queue[Trie] = new util.LinkedList[Trie]()
    var count = 0l

    def traversal:Unit={
      val eCount = trie.nextElement.filter(x => !x._2.nextElement.isEmpty).map{x => queue.add(x._2); 1}.sum
      count += eCount
      if(queue.size()!=0) traversal
    }
    traversal
    count
  }
}


case class Trie(n:Char, nextElement:HashMap[Char, Trie] = HashMap.empty[Char, Trie], name:String = null)