package cn.tedu.median

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("median")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/median.txt", 2)
    
    val r1=data.flatMap {_.split(" ")}.map{_.toInt}.sortBy{num=>num}.collect
               
    r1.foreach {println}
    println()
    val r2=r1(r1.length/2)
    println(r2)
    
    
    //r2.foreach(println)
    
              
  }
}