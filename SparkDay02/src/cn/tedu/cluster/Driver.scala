package cn.tedu.cluster

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  
  def main(args: Array[String]): Unit = {
    
    //setMaster("spark://hadoop01:7077"):设置为集群的运行模式
    val conf=new SparkConf().setMaster("spark://hadoop01:7077").setAppName("wordcount")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("hdfs://hadoop01:9000/01.txt",3)
    
    val wordcount=data.flatMap { _.split(" ") }.map{(_,1)}.reduceByKey{_+_}
    
    wordcount.saveAsTextFile("hdfs://hadoop01:9000/2008-result01")
    
    
  }
}