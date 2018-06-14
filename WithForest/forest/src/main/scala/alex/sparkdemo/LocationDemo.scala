package alex.sparkdemo

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by zhiguang on 2018/4/21.
 */
object LocationDemo {
    def main(args: Array[String]) {
        //val conf = new SparkConf()
        val conf = new SparkConf().setAppName("IPLocation").setMaster("local")
        val sc = new SparkContext(conf)
        val rdd1 = sc.textFile("f://spark//log.txt").map( line =>{
            val fields = line.split(",")
            val name = fields(0)
            val age = fields(1)
            (name,age)
        })

        //全部的ip映射规则
        val arr = rdd1.collect()

        //广播规则
        val dim_name = sc.broadcast(arr)
        val rdd2 = sc.textFile("f://spark//namelist.txt").map( line=>{
            val fields = line.split(",")
            val name = fields(0)
            val city = fields(1)
            (name,city)
        })

        Thread.sleep(200)

        rdd1.join(rdd2).collect().map( x=>{
            val name = x._1
            val age = x._2._1
            val address = x._2._2
            (name,age,address)
        }).map( println(_))

        sc.stop()

    }
}
