package cn.tedu.sgd

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LinearRegressionWithSGD

/**
 * 处理data目录下的testSGD.txt
 * Y,X1 X2
 * 1,0 1
	 2,0 2
   3,0 3
   5,1 4
   7,6 1
 *
 * 建立多元线性回归模型
 * Y=β1X1+β2X2
 * 
 * 底层使用梯度下降法来求解系数
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("sgd")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/testSGD.txt")
    
    //第一步:为了满足建模需要,RDD[line:String]->RDD[LabeledPoint]
    val r1=data.map { line =>
       val arr=line.split(",")
       val Y=arr(0).toDouble
       val X1=arr(1).split(" ")(0).toDouble
       val X2=arr(1).split(" ")(1).toDouble
       
       LabeledPoint(Y,Vectors.dense(X1,X2))
    }
    
    //第二步:建模。建立的是多元线性回归模型。底层使用梯度下降法求解
    //①参:数据集   ②参:最大的迭代次数  ③参:步长
    val model=LinearRegressionWithSGD.train(r1,10,0.05)
    
    //获取自变量系数
    val coef=model.weights
    
    //第三步:回代原数据集，通过模型方程得出预测结果。要求传入的数据类型:RDD[Vector(X1,X2)]
    val result=model.predict(r1.map { lb => lb.features })
    
    //练习1:给定下列数据,通过模型预测对应的Y值
    //X1 X2
    //4 9
    //3 7
    //2 9

    val r2=sc.parallelize(List((4,9),(3,7),(2,9)))
             .map{x=>Vectors.dense(x._1,x._2)}
    
    
    val result02=model.predict(r2)
    
  }
}