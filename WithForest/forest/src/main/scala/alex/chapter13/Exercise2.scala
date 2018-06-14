package main.scala.alex.chapter13

import scala.collection.mutable.ListBuffer

/**
 * Created by zhiguang on 2018/4/21.
 */
object Exercise2 {
    def main(args: Array[String]) {
        //第三题: 编写一个函数, remove 一个listbuffer中的每第二个元素
        //用两种方式实现, 一种是直接去除, 一种是把第二个元素copy到一个新的listbuffer, 然后去除
        //比较两种方式

        var listbuff = ListBuffer(1,2,3,12,22,33,0,45)
        var mark=0
        var listbuff2 = ListBuffer


        //第四题
        val arr = Array("Tom","Jim","Jack")
        val map = Map("Tom"->3, "Fred"->4,"Jack"->5,"Hank"->6)
        func_4(arr,map).map(println(_))
        func_5(arr,map).map(println(_))

        //第五题
        val list5 = List("Tom","Jim","Jack")
        fun_mkstring(list5)



    }

    def removeSecond(mark:Int, listbuff:ListBuffer[Int])={
        //第三题没做出来
    }

    def func_4(arr:Array[String], map:Map[String,Int]):Map[String,Int]={
        import scala.collection.mutable.Map
        var map2 : Map[String,Int] = Map()
        for(e <- arr){
            if(map.contains(e)){
                map2.+=(e -> map(e))
            }
        }
        map2.toMap
    }

    def func_5(arr:Array[String], map:Map[String,Int]):Array[Int]={
        import scala.collection.mutable.ArrayBuffer
        val arr_buf = new ArrayBuffer[Int]()
        for(e <- arr){
            if(map.contains(e)){
                arr_buf += map(e)
            }
        }
        arr_buf.toArray
    }


    def fun_mkstring(list:List[String]):String={
        val res = list.reduceLeft( (x,y)=>{
            x.toString + y.toString
        } )
        println(res)
        res
    }


}
