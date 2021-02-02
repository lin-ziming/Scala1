package cn.tedu.storage

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("storage")
    
    val sc=new SparkContext(conf)
    
    //设定检查点目录路径
    //hdfs://hadoop01:9000/check
    sc.setCheckpointDir("e://data/check")
    
    val data=sc.textFile("e://data/word.txt",2)
    
    //经验上，可以将数据源RDD持久化到内存中。
    data.cache()
    
    val r1=data.flatMap { line => line.split(" ") }
    
    //可以将整个计算链处于中游位置的RDD进行持久化。
    //cache()和persist()的持久化级别都是memory_only
    //persist()可以指定其他的持久化级别
    val r2=r1.map { word => (word,1) }
    r2.persist(StorageLevel.MEMORY_AND_DISK)
    //设置检查点
    r2.checkpoint()
    
    val r3=r2.reduceByKey{_+_}
    
    r3.foreach{println}
    
    //清除持久化级别
    data.unpersist()
    r2.unpersist()
  }
}