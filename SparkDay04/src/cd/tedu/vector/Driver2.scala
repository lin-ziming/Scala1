package cd.tedu.vector

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver2 {
  def main(args: Array[String]): Unit = {
    
    val v1=Vectors.dense(2.1,3.5,3.3)
    
    val lb=LabeledPoint(10.0,v1)
    
    val r1=lb.label
    val r2=lb.features
    
    println(lb)
    
    val conf=new SparkConf().setMaster("local").setAppName("vector")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("d://sparkTest/data/vectors.txt", 2)
    
    val r3=data.map { line => 
        val arr=line.split(" ").take(2).map { num => num.toDouble } }
    
    
    val r4=data.map{line=>
        val arr=line.split(" ")
        val Y=arr.last.toDouble
        val X1=arr(0).toDouble
        val X2=arr(1).toDouble
        
        LabeledPoint(Y,Vectors.dense(X1,X2))
    }
    r4.foreach(println)
    
    val r5=data.map{line=>
        val arr=line.split(" ")
        val Y=arr.last.toDouble
        
        val XArr=arr.dropRight(1).map{num=>num.toDouble}
        
        LabeledPoint(Y,Vectors.dense(XArr))
    }
    r5.foreach(println)
    
    
    //val lb1=LabeledPoint(r4,Vectors.dense(r4))
    
  }
}