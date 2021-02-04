package cn.tedu.sgd

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
/**
 * 
 */
object Driver {
  def main(args: Array[String]): Unit = {
      val conf=new SparkConf().setMaster("local").setAppName("sgd")
      val sc=new SparkContext(conf)
      
      val data=sc.textFile("d://2008scala/sparkTest/data/testSGD.txt")
      
      //第一步
      val r1=data.map { line => 
        val arr=line.split(",") 
        val Y=arr(0).toDouble
        val X1=arr(1).split(" ")(0).toDouble
        val X2=arr(1).split(" ")(1).toDouble
        
        LabeledPoint(Y,Vectors.dense(X1,X2))    
      }
      r1.foreach { println }
      
      //第二步
      val model=LinearRegressionWithSGD.train(r1, 10, 0.05)
      
      val coef=model.weights
      println(coef)
      
      //第三步
      val result=model.predict(r1.map { lb => lb.features })
      result.foreach { println  }
      
      
      //练习1
      val r2=sc.parallelize(Array((4,9),(3,7),(2,9)))
               .map{ x=>Vectors.dense(x._1,x._2) }
      
      //val result2=m
  }  
}