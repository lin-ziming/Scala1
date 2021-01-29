package cn.tedu.avg

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("avg")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/average.txt", 2)
    
    val r1=data.map{_.split(" ")(1).toInt}
    println(r1.collect)
    val avg=r1.sum/r1.collect.length
    val avg1=r1.sum/r1.count()
    println(avg)
    println(avg1)
  }
}