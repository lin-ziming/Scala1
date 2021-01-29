package cn.tedu.wordcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    //创建
    val conf=new SparkConf().setMaster("local").setAppName("wordcount")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("D://sparkTest/data/word.txt",2)
    
    val wordcount=data.flatMap{ line => line.split(" ") }
                      .map { word => (word,1) }
                      .reduceByKey{_+_}
            
    wordcount.foreach{println}
    
  }
}