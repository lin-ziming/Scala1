package cn.tedu.avg

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 求出 data目录下的average.txt。求出第二列的均值
 * 结果打印到控制台即可
 * 
 * 
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
      
    val conf=new SparkConf().setMaster("local").setAppName("avg")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/average.txt",2)
    
    val r1=data.map { line => line.split(" ")(1).toInt }
    
    val avg=r1.sum/r1.count()
    
    println(avg)
    
    
    
  }
}