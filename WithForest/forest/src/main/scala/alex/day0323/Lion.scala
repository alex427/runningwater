package alex.day0323

/**
 * Created by zhiguang on 2018/4/7.
 */
object Lion {
    def main(args: Array[String]) {
        val arr = Array(12,2,3,23,56,7,8,4,19,20,30)
        //各种聚合操作, 包括并行化的聚合操作: 必须掌握
        //grouped(size) 对集合进行分组, 返回一个list嵌套结构
        val res1 = arr.grouped(4).map(x=>x.sum)
        println(res1.toList)
        //val arr1 = arr.par.aggregate(0)(_+_.sum,_+_)
        //println(arr1)
        //res1.isInstanceOf()

        val t1, t2, (a,b,c)={
            println("ABC")
            (1,2,3)
        }


    }
}
