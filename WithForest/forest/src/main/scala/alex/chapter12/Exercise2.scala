package alex.chapter12

import scala.collection.GenSeq

object Exercise2 {

    def main(args: Array[String]): Unit = {

        //第二题:
        //如何用reduceLeft获取数组中的最大值?
        //reduceLeft需要一个函数作为参数, 他将该函数从左开始应用在集合的每一个元素上面
        val arr = Array(2, 23, 22, 34, 25, 56, 75, 27, 12)
        val res2 = arr.reduceLeft((x, y) => {
            if (x > y) x else y
        })
        println(res2)


        //第三题:
        //用to 和 reduceLeft 实现阶乘函数, 不得使用递归
        val res3 = 1.to(10).reduceLeft((x, y) => {
            x * y
        })
        println(res3)

        //第四题:
        //前一个实现需要处理一个特殊情况, 即n<1的情况. 展示如何用foldLeft来避免这个需要.
        val res4 = 1.to(10).foldLeft(1)((x, y) => {
            x * y
        })
        println(res4)

        //第五题
        //编写函数largest(fun:(Int)=>Int, inputs:Seq[Int]), 输出在给定输入序列中给定函数的最大值.
        //举例来说, largest(x=>10*x - x*x, 1 to 10) 应该返回25. 不得使用循环或递归
        def largets(fun: (Int => Int), inputs: Seq[Int]) = {
            inputs.map(fun(_)).reduceLeft((x, y) => {
                if (x > y) x else y
            })
        }

        val res5 = largets(x => {
            10 * x - x * x
        }, 1.to(10))
        println(res5)

        //第六题
        //修改前一个函数, 返回最大的输出对应的输入. 举例来说, largestAt(fun:(Int)=>Int, intpus:Seq[Int])应该返回5
        val res6 = largets(x => x / 2, 1.to(10))
        println(res6)

        //第七题
        //要得到一个序列的对偶很容易, 比如:  val pairs = (1 to 10) zip (10 to 20)
        //假定你想要对这个做某种操作---比如, 给对偶中的值求和, 但不可以直接 pairs.map(_+_)
        //函数_+_接受两个Int作为参数, 而不是(Int,Int)对偶. 编写函数adjustToPair, 该函数接受一个类型为
        // (Int, Int)=>Int 的函数作为参数, 并返回一个等效的, 可以以对偶作为参数的偶数. 举例来说:
        // adjustToPair(_*_)((6,7)) 得到42. 然后用这个函数通过map计算各个对偶的元素之和.
        val pairs = (1 to 10) zip (11 to 20)
        println(pairs) //Vector((1,11), (2,12), (3,13), (4,14), (5,15), (6,16), (7,17), (8,18), (9,19), (10,20))
        val res7 = pairs.map(x => x._1 + x._2).map(println(_))

        def adjustToPair = (fun: (Int, Int) => Int) => ((t: Tuple2[Int, Int]) => {
            fun(t._1, t._2)
        })

        val res8 = adjustToPair((x, y) => x * y)((6, 7))
        println(res8)
        println("----")
        val res9 = pairs.map(adjustToPair((x, y) => x * y)(_)).map(println(_))

        //第八题
        //在12.8节中, 你看到了用于两组字符串数组的corresponds方法. 做出一个对该方法的调用,
        // 让它帮我们判断某字符串数组里的所有元素的长度是否和某个给定的整数数组相对应.

        val a = Array("Jim", "Jack")
        val b = Array("jim", "jack")
        val c = Array(33, 55)
        a.corresponds(b)(_.equalsIgnoreCase(_))
        //x是a的元素, y是b的元素
        //corresponds的第二参数是一个函数, 称为前提函数, 前提函数返回值是Boolean; corresponds方法的返回值也是Boolean; 整体表现为柯理化的过程
        //   def corresponds[B](that: GenSeq[B])(p: (A,B) => Boolean): Boolean = { ... }
        a.corresponds(b)((x: String, y: String) => x.equalsIgnoreCase(y))
        a.corresponds(c)((x: String, y: Int) => x.equalsIgnoreCase(y.toString))
        println(a.corresponds(b)(_.equalsIgnoreCase(_))) //true

        val aa = Array("Jim", "Jack", "Candy", "Jeremy", "Johnson","zuck")
        val bb = Array(3, 4, 5, 6, 7)
        val res10 = aa.corresponds(bb)((x: String, y: Int) => {
            if (x.length() == y)
                true
            else
                false
        })
        println(res10)

        //第九题
        //不使用科里化来实现corresponds

        //这种定义函数的方式, 似乎不好, 无法给出泛型
       /* val mycorrespond = (that:GenSeq[B], func:(A,B)=>Boolean ) =>{
            true
        }*/

        /*def mycorrespond[B](that:GenSeq[B], func:(A,B)=>Boolean ) ={
           true
         }*/

        //第十题
        //实现一个unless控制抽象,工作机制类似if, 但是条件反过来.
        // 第一个参数需要是换名调用的参数吗? 需要科里化吗?

        //注意冒号后面, 那里省略了(), 表示没有参数. 这里最好省略掉, 否则下面使用这个函数时, 不好用.
        def unless(condition: =>Boolean)(block: =>Unit) = {
            while ( !condition ){
                block
            }
        }

        var x =20
        unless( x<10 )({
            println(x)
            x -= 1
        } )


    }

}
















