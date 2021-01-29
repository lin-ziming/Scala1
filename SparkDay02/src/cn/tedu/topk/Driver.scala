package cn.tedu.topk

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("topk")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/topk.txt", 2)
    
    val r1=data.flatMap{_.split(" ")}
               .map { word => (word,1) }
               .reduceByKey(_+_)
               .sortBy{ -_._2 }.take(3)
    r1.foreach{println}
               
               
   val wordcount=data.flatMap{_.split(" ")}
               .map { word => (word,1) }
               .reduceByKey(_+_)
   val top3=wordcount.sortBy{ -_._2 }.take(3)
   top3.foreach{println}
  }
}