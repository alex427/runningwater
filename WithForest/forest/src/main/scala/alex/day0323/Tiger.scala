package alex.day0323

import scala.collection.immutable.HashSet

object Tiger {

    def main(args: Array[String]): Unit = {
        //Scala的集合有三大类：序列Seq、Set、Map，全部extends Iterable特质
        //trait Map[A, +B] extends scala.AnyRef with scala.collection.immutable.Iterable[scala.Tuple2[A, B]]
        //源码太复杂, 没咋搞明白
        //在Scala中集合有可变（mutable）和不可变（immutable）两种类型，immutable类型的集合初始化后就不能改变了（注意与val修饰的变量进行区别）


        //定义一个map
        //map有两种, 默认是immutable, 如果要是用muttable的, 需要单独引入
        //val map1 = Map( "cortisol" -> 5, "serotonin" -> 10, "adreneline" -> 115, "dophamin" -> 25, "vasodilatation" -> 35 )
        val map2 = Map( ("cortisol",2),("serotonin",3),("adreneline",5),("dophamin",25) )
        val v = map2.get("serotonin")
        println(v+"  "+v.getClass)

        val map3 = Map(("jack",3),("mack",32),("zuck",13),("hack",333),("nack",1333))
        val v2 = map3("mack")
        val v3 = map3.getOrElse("duck", 0)
        println(v2)
        println(v3)



        //immutable set, 和java的set一样, 元素不重复, 唯一
        //创建一个空set
        val set1 = new HashSet[Int]()
        //将元素和set1合并生成一个新的set，原有set不变
        val set2 = set1 + 4
        //++操作直接追加一个相同泛型的set, 生成一个新的set
        val set3 = set1 ++ Set(5, 6, 7, 3, 4)
        val set0 = Set(1,3,4) ++ set1 ++ set3
        println(set1)  //Set()
        println(set2)  //Set(4)
        println(set3)  //Set(5, 6, 7, 3, 4)
        println(set0) //Set(5, 1, 6, 7, 3, 4)
        println(set0.getClass) //scala.collection.immutable.Set$Set3





    }

}
