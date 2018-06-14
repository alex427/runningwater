package alex.sparkdemo

import org.apache.spark.memory.UnifiedMemoryManager
import org.apache.spark.util.SizeEstimator
import org.apache.spark.{SparkConf, SparkContext}

object SparkDemo {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local").setAppName("demo")
        val sc = new SparkContext(conf)
        val rdd1 = sc.textFile("F:/log.txt")
        //rdd1.take(3).map(println(_ ))

        val msize = SizeEstimator.estimate(rdd1)
        println(msize)

       // UnifiedMemoryManager
    }
}
