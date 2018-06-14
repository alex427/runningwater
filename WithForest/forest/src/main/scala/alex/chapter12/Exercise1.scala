package alex.chapter12

import scala.collection.mutable.{ArrayBuffer, ListBuffer}


object Exercise1 {
    //第一题:
    //编写函数values(fun:(Int) => Int, low:Int, high:Int), 该函数输出一个集合, 对应给定区间内给定函数的输入和输出.
    //比如, values(x=>x*x, -5, 5)应该产生一个对偶的集合(-5,25,),(-4,16),(-3,9),...(5,25)
    def main(args: Array[String]): Unit = {
        val list = produceTuple(-5,5)
        list.map(println(_ ))

        val list2 = produceTupleFunc(1,10).map(println(_ ))

        val list3 = produceTupleFunc2(2)(8).map(println(_ ))

    }

    def produceTuple(low: Int, high: Int): List[Tuple2[Int,Int]] = {
        //var list = new List[Tuple2[Int]]  //list属于immutable集合, 在这里不好用
        //ArrayBuffer, ListBuffer更合适
        val buf = new ListBuffer[Tuple2[Int,Int]]()
        //val buf = new ArrayBuffer[Tuple2[Int,Int]]()

        low.to(high).map(x => {
            val tuple = Tuple2(x, x * x)
            buf += tuple
            //(x, x * x) :: list   //list头部追加元素, 但是产生一个新的list

        })
        buf.toList
    }
    
    val produceTupleFunc =  (low: Int, high: Int) => {
        val buf = new ListBuffer[Tuple2[Int,Int]]()
        low.to(high).map(x => {
            val tuple = Tuple2(x, x * x)
            buf += tuple
        })
        buf.toList
    }

    //采用科里化
    def produceTupleFunc2 = (low:Int) => ((high:Int)=>{
        val buf = new ListBuffer[Tuple2[Int,Int]]()
        low.to(high).map( x =>{
            val tuple = Tuple2(x, x*x)
            buf += tuple
        })
        buf.toList
    })

}
