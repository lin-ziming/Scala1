package cn.tedu.maxmin

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver02 {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("fehight")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/MaxMin.txt", 2)
    
    val r1=data.filter { line => line.split(" ")(1)=="F" }
               .sortBy{line => line.split(" ")(2).toInt }
               .take(2)
    
    r1.foreach{println}
    
  }
}