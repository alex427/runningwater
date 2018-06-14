package alex.sparkdemo.withkafka

import org.apache.avro.mapred.SequenceFileInputFormat
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.{IntWritable, LongWritable}
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Created by zhiguang on 2018/5/3.
 */
class KafkaWithStreaming {

}

object KafkaWithStreaming{
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("WithKafka").setMaster("local[2]")
        val ssc = new StreamingContext(conf,Seconds(10))

        //另外一种获取StreamingContext的方法, 这种方式能够解决driver failure tolerance
        //val hadoopConf: Configuration = new Configuration()
        //StreamingContext.getOrCreate("hdfs://ck/dir1/",createSsc, hadoopConf, true)

        ssc.checkpoint("hdfs://ck/dir1/")
        /*def createStream(
                                ssc: StreamingContext,
                                zkQuorum: String,
                                groupId: String,
                                topics: Map[String, Int],
                                storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2
                                ): ReceiverInputDStream[(String, String)] = {*/
        //参数准备
        val zkQuorum = "zk1";
        val groupId = "group1";
        val topics = Map("topic_data"->2)  //2个消费线程
        val storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2

        val dstream = KafkaUtils.createStream(ssc,zkQuorum,groupId,topics,storageLevel)
        val words = dstream.map(_._2).flatMap( x=>x.split(" "))
        //val counts = words.map( x=>(x,1)).reduceByKey( (x,y)=>{x+y})
        val counts = words.map( x=>(x,1)).updateStateByKey(func,new HashPartitioner(ssc.sparkContext.defaultParallelism),true)
        counts.saveAsTextFiles("path")

        //这里有一个偏函数应用
        //ssc.fileStream[LongWritable,IntWritable,SequenceFileInputFormat[LongWritable,IntWritable]]("inuputDir").map{ case (x,y) =>(x.get(),y.get())}

        ssc.start()
        ssc.awaitTermination()
    }

    val func = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
        //iter.flatMap(it=>Some(it._2.sum + it._3.getOrElse(0)).map(x=>(it._1,x)))
        iter.flatMap { case (x, y, z) => Some(y.sum + z.getOrElse(0)).map(num => (x, num) ) }
    }

    val createSsc = () =>{
        val conf = new SparkConf().setAppName("WithKafka").setMaster("local[2]")
        val ssc = new StreamingContext(conf,Seconds(10))
        ssc.checkpoint("hdfs://ck/dir1/")
        ssc
    }


}
