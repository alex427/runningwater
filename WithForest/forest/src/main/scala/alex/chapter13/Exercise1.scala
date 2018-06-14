package alex.chapter13

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Exercise1 {

    def main(args: Array[String]): Unit = {

        val digits = List(4, 2) //class scala.collection.immutable.$colon$colon
        //::操作符
        val aa = 9 :: digits
        println(digits.getClass + "  " + digits)
        println(aa.getClass + "  " + aa)

        val ab = 5 :: 3 :: 2 :: 51 :: Nil
        println(ab.getClass + "  " + ab)

        println(qiuhe(ab))
        println(qiuhe2(ab))

        //这个已经被废弃了, 改为ListBuffer
        val list2 = scala.collection.mutable.LinkedList(3, 5, -5, 34, 5, 6, -3, 56)
        var cur = list2
        while (cur != Nil) {
            if (cur.elem < 0) {
                cur.elem = 0
            }
            cur = cur.next
        }
        //cur.map(println(_))

        //把list中小于0的数字, 全部替换为0
        import scala.collection.mutable._
        val list3 = ListBuffer(3, 5, -5, 34, 5, 6, -3, 56)

        var cur3 = list3
        /*for (x <- cur3){
            if(x<0){
                x=0
            }
        }*/
        /*cur3.map( (x)=>{
            if(x<0){
                x=0
            }
        } )*/

        //Set
        //Set元素唯一, 没有顺序, 不接受null元素, 这和java不一样, 否则报错 Flat hash tables cannot contain null elements.
        val set = Set(22,3,12,5,56,45)
        set.map( (x)=>{print(x+"  ")} )

        //带插入顺序的Set, 这个可以接受null
        val linkedhashset = scala.collection.mutable.LinkedHashSet("fisrt","second","third","forth","fifth", null)
        val linkedhashset2 = scala.collection.mutable.LinkedHashSet(22,3,12,5,56,45, null)
        linkedhashset.map((x)=>{print(x+"  ")})
        linkedhashset2.map((x)=>{print(x+"  ")})

        //带排序顺序的Set
        val sortedset = scala.collection.mutable.SortedSet(22,12,5,3,4,6)
        sortedset.map( (x)=>{ println(x) } )

        //BitSet
        //BitSet据说速度比较快, 但实际上, 我没有搞明白
        val bitset = BitSet(1,2,35,3)
        println(bitset)
        bitset.map( (x)=>{println(x)} )

        //SetApi练习
        //是否包含某个元素
        val res = set.contains(3)
        println(res)

        //当前set是否是目标set的子集
        val res2 = Set(3,2).subsetOf(set)
        println(res2)

        //求并集, 多种表示方式
        val set2 = Set(12,3,33,5,26,78)
        val res3 = set.union(set2)
        val res4 = set | set2
        val res5 = set ++ set2
        println(res3+"     "+res4+"    "+res5)

        //求差集, 多种表示方式
        val res6 = set.diff(set2)
        val res7 = set &~ set2
        val res8 = set -- set2
        println(res6+"     "+res7+"    "+res8)

        //求交集, 多种表示方式
        val res9 = set.intersect(set2)
        val res10 = set & set2
        println(res9+"     "+res10)


        val namelist = List("Peter","James","Mary","Jeremy")
        //groupby
        //  def groupBy[K](f: A => K): immutable.Map[K, Repr] = { ... }
        val res11 = namelist.groupBy(_.substring(1,3))
        res11.map( println(_) )
        /*  (am,List(James))
          (et,List(Peter))
          (ar,List(Mary))
          (er,List(Jeremy))*/


        //namelist.flatMap(pro_str(_))
        namelist.toBuffer.transform(_.toLowerCase).map(println(_))

        val set12 = Set(12,3,33,5,26,78)
        val res12 = set12.reduceLeft( (x,y)=>{x+y} )
        println(res12)

        val res13 = set12.foldLeft(1000)( (x,y)=>{x+y} )
        println(res13)

        val list12 = List(12,3,33,5,26,78)
        val res14 = list12.scanLeft(1500)( (x,y)=>{x+y} )
        println(res14)

        val list13 = List(1.5,2.2,3.2,5.5,3.0,2.0)
        list12.zip(list13).map {x => x._1 * x._2}.map(println(_))
        list12.zip(list13).map( x => x._1 * x._2 ).map(println(_))

        val iterator = list12.iterator
        //这个操作, 非常有实际意义
        val buff_iter = iterator.buffered
        val head = buff_iter.head


        //java scala之间的转换器, 这个隐式转换, 采用的是装饰者模式

       // implicit def propertiesAsScalaMap(p: ju.Properties): mutable.Map[String, String] = p match {
       //     case _ => new JPropertiesWrapper(p)
        //   }
        import scala.collection.JavaConversions.propertiesAsScalaMap
        val props: scala.collection.mutable.Map[String,String] = System.getProperties
        props.put("AAName","AAtensorflow")


        val list15 = List[Long]()
        for(ele <- 1.to(99999999)){
            ele :: list15
        }
        val t1 = System.currentTimeMillis()
        val res15 = list15.sum
        println(System.currentTimeMillis() - t1)

        val t2 = System.currentTimeMillis()
        val res16 = list15.par.sum
        println(System.currentTimeMillis() - t2)

        //这就奇怪了, 并行集合的速度还要慢一些.

        println( res15.toString + "   "+ res16.toString)




        //第一题: 编写一个函数, 给定字符串, 产出一个包含所有字符的下标的映射. 举例来说, indexes("Mississippi")应该
        //返回一个映射, 让M对应集{0}, 'i'对应集{1,4,7,10}, 依此类推. 使字符到可变集的映射. 另外, 你如何保证集是经过排序的?
        val str = "Mississippi"
        func_1(str).map(println(_))
        func_2(str).map(println(_))

        //第二题, 重复第一题, 采用字符到immutable collection的映射


        Thread.sleep(100)
        //Java的公有API可以主动调用GC的有两种办法，一个是
        System.gc();
        // 或者下面，两者等价
        //Runtime.getRuntime().gc();

        Thread.sleep(200)
    }


    def func_1(str:String):Map[Char,List[Int]]={
        val rr = 0.to (str.length()-1)
        val index_list = rr.toList
        val ele_list = str.toList
        ele_list.zip(index_list).groupBy[Char]( x=>x._1 ).map( x=>{
           val mm = x._2.map( x=>{x._2} )
          (x._1,mm)
        })
    }

    import scala.collection.mutable.Buffer
    def func_2(str:String):Map[Char,Buffer[Int]]={
        val index_list = 0.to(str.length -1).toList.toBuffer
        val ele_list = str.toList.toBuffer
        ele_list.zip(index_list).groupBy[Char]( x=>x._1 ).map( x=>{
            (x._1, x._2.map( x=>x._2 ) )
        } )
    }


    //List的特殊结构, 可以允许如下这种递归求和方法
    //除了传统的if..else..流程控制, 还可以采用scala模式匹配来实现
    def qiuhe(list: List[Int]): Int = {
        if (list == Nil) {
            return 0
        } else {
            return list.head + qiuhe(list.tail)
        }
    }

    def qiuhe2(list: List[Int]): Int = list match {
        case Nil => 0
        case h :: t => {
            h + qiuhe2(t)
        }
    }



}
