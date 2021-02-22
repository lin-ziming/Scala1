package cn.tedu.test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

object Driver {
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("streaming")
    
    val sc=new SparkContext(conf)
    
    val ssc=new StreamingContext(sc,Seconds(3))
    
    val source=ssc.textFileStream("hdfs://hadoop01:9000/data")
    
    val wordcount=source.flatMap { _.split(" ") }.map{(_,1)}.reduceByKey{_+_}
    
    wordcount.print()
    
    ssc.start()
    
    //作用：保持SparkStreaming一直开启，直到用户主动中断为止
    ssc.awaitTermination()
    
  }
}