package cn.tedu.maxmin

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 处理MaxMin.txt,返回女性身高最小的前两条数据
 * 比如返回的结果:
 * 
 * 5 F 160
 * 6 F 162
 * 
 * 
 */
object Driver02 {
  
  def main(args: Array[String]): Unit = {
    //ctrl+1
    val conf=new SparkConf().setMaster("local").setAppName("maxmin")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/MaxMin.txt")
    
    //RDD[line:String]->RDD[女性的line:String]->按身高升序排序->取出前两名
    val r1=data.filter { line => line.split(" ")(1)=="F" }
               .sortBy{line=>line.split(" ")(2).toInt}
               .take(2)
               
    
    val r2=data.filter { line => line.split(" ")(1)=="F" }
               .map { line => line.split(" ")(2).toInt }
               .sortBy{x=>x}
               .take(2)
               
    r2.foreach{println}           
  }
}