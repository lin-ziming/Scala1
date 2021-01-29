package cn.tedu.maxmin

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("maxmin")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/MaxMin.txt", 2)
    
    val r1=data.filter{_.split(" ")(1)=="M"}
              .map { x => x.split(" ")(2).toInt }.max
    println(r1)
  }
}