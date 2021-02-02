package cn.tedu.topk

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 处理 topk.txt，返回单词频次最高的前3项单词数据，比如：
 * (hello,10)
 * (hive,6)
 * (hadooop,4)
 * 
 * 
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("maxmin")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/topk.txt")
    
    val wordcount=data.flatMap { line => line.split(" ") }
                      .map { word => (word,1) }
                      .reduceByKey{_+_}
                      
    val top3=wordcount.sortBy{x=> -x._2}.take(3)     
    
    top3.foreach{println}
                      
  }
  
}