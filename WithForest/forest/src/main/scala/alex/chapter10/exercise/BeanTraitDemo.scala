package alex.chapter10.exercise

import java.awt.Point
import java.beans.PropertyChangeListener

import scala.collection.mutable.ArrayBuffer

object BeanTraitDemo {

    def main(args: Array[String]): Unit = {

        //第五题
        //这样mypoint就能使用对应接口中的方法
        //val mypoint  = new MyPoint(10,10) with BeanPropertyChangeSupportTrait








        val arr = Array(1,2,3,-1,-2,-3)
        val buffer = new ArrayBuffer[Int]
        val b = arr.filter((x:Int)=>{x > (0)}).map((x:Int)=>{println(x)})


        val bb = arr.filter((x:Int)=>{x > (0)})
          .map( (x:Int)=>{
              buffer += (x)
              buffer.toArray
          } )
        println(bb.getClass)
        println(bb.toBuffer)

        val cc = for (ele <- arr if ele>0) yield ele
        println(cc.getClass)


        ex8
        scala.collection.immutable.Vector


    }

    def ex8: Unit = {
        val a = Array(1, -1, 2, -2, 3, -3, 4, -4, 5, -5)
        val b = a
        val c = ( for(i<- 0 until b.length if b(i)<0 ) yield i).reverse
        //c.trimEnd(1)
        //for(i<- c) b.remove(i)
        println(c.getClass)
    }

}
