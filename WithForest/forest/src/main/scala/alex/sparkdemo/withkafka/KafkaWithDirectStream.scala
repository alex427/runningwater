package alex.sparkdemo.withkafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

/**
 * Created by zhiguang on 2018/5/4.
 */
object KafkaWithDirectStream {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("WithKafka").setMaster("local[2]")
        val ssc = new StreamingContext(conf,Seconds(10))

        val topics = Set("teststreaming")
        val brokers = "kafka1:9092,kafka2:9092,kafka3:9092"
        val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers, "serializer.class" -> "kafka.serializer.StringEncoder")

        // Create a direct stream
        val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
        val messages = kafkaStream.flatMap(line => {
            Some(line.toString())
        })

        messages.foreachRDD(rdd => {
            if (!rdd.isEmpty()) {
                // 先处理消息
                //processRdd(rdd)
                // 再更新offsets
               // km.updateZKOffsets(rdd)
            }
        })

        ssc.start()
        ssc.awaitTermination()
    }
}
