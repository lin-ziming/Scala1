package cd.tedu.vector

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * 学习MLlib的Vector类型
 */
object Drvier {
  def main(args: Array[String]): Unit = {
    
    val v1=Vectors.dense(2.1, 3.5, 1.6)
    
    //println(v1(0))
    
    val v2=Vectors.dense(Array[Double](2.1,1.8,3.4))
    //println(v2)
    
    //练习1：
    val conf=new SparkConf().setMaster("local").setAppName("vector")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/vectors.txt", 2)
    
    val r1=data.map{line=>
      val arr=line.split(" ")  
      val x1=arr(0).toDouble
      val x2=arr(1).toDouble
      val x3=arr(2).toDouble
      
      Vectors.dense(x1,x2,x3)
    }
    r1.foreach { println }
    
    val r2=data.map { line => line.split(" ").map{num=>num.toDouble} }
              .map { arr => Vectors.dense(arr) }
    
    
    val v3=Vectors.dense(data.collect.map{_.toDouble})
    
    println(v3)
    //data.collect.foreach(println)
  }
}