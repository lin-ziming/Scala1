package cn.tedu.vector

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 学习MLlib的向量标签类型
 */
object Driver02 {
  
  def main(args: Array[String]): Unit = {
      
    val v1=Vectors.dense(2.1,3.5,3.3)
    
    //创建一个向量标签。
    //①参:标签值-因变量   ②参:由自变量所组成的向量
    val lb=LabeledPoint(10.0,v1)
    
    //获取标签值
    val r1=lb.label
    //获取向量
    val r2=lb.features
    
    //练习1:处理data目录下的vectors.txt
    /*X1 X2 Y
     * 2 1 3
			 5 2 1
       10 8 7
       5 6 4
     */
    //用spark处理,RDD[String]->RDD[LabeledPoint]
    val conf=new SparkConf().setMaster("local").setAppName("label")
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/vectors.txt")
    
    val r3=data.map { line =>
        val arr=line.split(" ")
        val Y=arr.last.toDouble
        val X1=arr(0).toDouble
        val X2=arr(1).toDouble
        
        LabeledPoint(Y,Vectors.dense(X1,X2))
    }
    
    
     val r4=data.map { line =>
        val arr=line.split(" ")
        val Y=arr.last.toDouble
        
        //获取自变量数组
        val XArr=arr.dropRight(1).map { num=>num.toDouble }
        
        LabeledPoint(Y,Vectors.dense(XArr))
        
    }
    
  }
}