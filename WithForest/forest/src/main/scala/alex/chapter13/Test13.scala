package alex.chapter13

import scala.collection.SortedSet
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Test13 {
  def ex01_indexes(s: String) = {
    var map = Map[Char, SortedSet[Int]]()
    s.zipWithIndex.foreach(x => {
      map += x._1 -> (map.getOrElse(x._1, SortedSet[Int]()) + x._2)
    })
    map
  }

  def ex02_indexes(s: String) = {
    var map = Map[Char, List[Int]]()
    s.zipWithIndex.foreach(x => {
      map += x._1 -> (map.getOrElse(x._1, List[Int]()) :+ x._2)
    })
    map
  }

  def ex03(lb: ListBuffer[Int]) = {
    lb.foreach(x => if (x == 0) lb -= 0)
    lb
  }

  def ex04(array: Array[String], map: Map[String, Int]) = {
    var t = ArrayBuffer[Int]()
    array.zip(map).foreach(x => if (x._1 == x._2._1) t += x._2._2)
    t.toArray
  }


  def main(args: Array[String]): Unit = {
    println(ex01_indexes("Mississippi"))
    println(ex02_indexes("Mississippi"))
    println(ex03(ListBuffer(0, 1, 2, 0, 3, 4)))
    println(ex04(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)).mkString(","))
  }
}
