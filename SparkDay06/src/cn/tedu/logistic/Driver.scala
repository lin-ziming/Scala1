package cn.tedu.logistic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS

/**
 * 处理data目录下的lg.txt
 * X1 X2 X3 Y
 * 17	1	1	1
	 44	0	0	1
	 48	1	0	1
	 55	0	0	1
	 75	1	1	1
	 35	0	1	0
 *
 * 基于样本数据，建立逻辑回归模型。
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
     val conf=new SparkConf().setMaster("local").setAppName("logistic")
     
     val sc=new SparkContext(conf)
     
     val data=sc.textFile("e://data/lg.txt")
     
     //第一步:为了满足建模需要,RDD[line:String]->RDD[LabeldPoint]
     val r1=data.map { line =>
       val arr=line.split("\t")
       val Y=arr.last.toDouble
       
       //获取自变量数组
       val XArr=arr.dropRight(1).map { num => num.toDouble }
       
       LabeledPoint(Y,Vectors.dense(XArr))
     }
     
     //第二步:建立逻辑回归模型，底层使用随机梯度下降法来求解系数
     //val model=LogisticRegressionWithSGD.train(r1,20,0.015)
     
     //建立逻辑回归模型，底层使用牛顿法来求解系数。
     val model=new LogisticRegressionWithLBFGS().run(r1)
     
     //获取自变量系数
     val coef=model.weights
     
     
     //第三步:回代原数据集,和原来的真实结果比较。判断分类结果是否正确
     val result=model.predict(r1.map { lb => lb.features })
     
     
     //练习1:处理data目录的testlg.txt,利用模型判断他们是否出现过交通事故
     /*
      * 18 0 0
				25 1 1
				30 0 1
				55 0 0
			*
      */
     val testData=sc.textFile("e://data/testlg.txt")
     
     //RDD[line:String]->RDD[Vector(X1,X2,X3)]
     val r2=testData.map { line => line.split(" ").map { num => num.toDouble }}
                    .map { arr => Vectors.dense(arr) }
     
     val result02=model.predict(r2)
     
     result02.foreach{println}
  }
  
}