package alex.sparkstreamingdemo

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by zhiguang on 2018/5/1.
 */
object StreamingDemo {

    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("aaa")
        val sc = new SparkContext(conf)
        val ssc = new StreamingContext(sc,Seconds(10))
    }

}
