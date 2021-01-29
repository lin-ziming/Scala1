package cn.tedu.cluster

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("spark://hadoop01:7077").setAppName("")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("hdfs://hadoop01:9000/01.txt", 3)
    
  }
}