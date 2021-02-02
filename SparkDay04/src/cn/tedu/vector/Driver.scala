package cn.tedu.vector

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 学习MLlib的Vector类型。
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    //创建一个向量
    val v1=Vectors.dense(2.1,3.5,1.6)
    
    //通过下标操作,下标从0开始
    println(v1(0))
    
    //传入一个Array[Double]来创建向量。
    val v2=Vectors.dense(Array[Double](2.2,1.8,3.4))
    
    //练习1:通过spark处理data目录的vectors文件。
    //完成转换RDD[String]->RDD[Vectors]
    //返回一个RDD[Vector]
    
    val conf=new SparkConf().setMaster("local").setAppName("vector")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/vectors.txt")
    
    val r1=data.map { line =>
      val arr=line.split(" ")  
      val x1=arr(0).toDouble
      val x2=arr(1).toDouble
      val x3=arr(2).toDouble
      
      Vectors.dense(x1,x2,x3)
    }
    
    val r2=data.map { line => line.split(" ").map { num =>num.toDouble } }
               .map { arr => Vectors.dense(arr) }
    
    r2.foreach{println}
  }
}