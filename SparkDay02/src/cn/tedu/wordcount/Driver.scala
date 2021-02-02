package cn.tedu.wordcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object Driver {
  
  def main(args: Array[String]): Unit = {
    
    //创建Spark环境参数对象。必须要设置运行模式和应用程序id
    //setMaster("local") 以本地单机模式运行
    //setAppName 设置应用程序id,自定义的
    val conf=new SparkConf().setMaster("local").setAppName("wordcount")
   
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/word.txt",2)
    
    val wordcount=data.flatMap { line => line.split(" ") }
                      .map { word => (word,1) }
                      .reduceByKey{_+_}
                      
    wordcount.foreach{println}                 
  }
}