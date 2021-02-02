package cn.tedu.maxmin


import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/**
 * 求出MaxMin.txt 男性最大的身高数据。
 * 结果打印控制即可
 * 
 * 
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("maxmin")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/MaxMin.txt")
    
    
    val r1=data.filter { line => line.split(" ")(1)=="M" }
               .map { line => line.split(" ")(2).toInt }
               .max
    println(r1)          
  }
}