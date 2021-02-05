package cn.tedu.movie

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.ALS

/**
 * 处理data目录下的u.data。建立推荐系统模型
 * 
 * 分隔符是制表符
 * 用户id  电影id  评分
 * 196	  242	   3	  
 * 
 * 迭代次数:5~15
 * k数量的选取。理论上,k越大,推荐会越准确。但k不宜过大,会导致计算代价过大。
 * 建议k的范围:10~50
 * 
 * 最后要求 为789号用户推荐10部电影。
 * 
 * 7分钟
 * 
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("als")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/u.data")
    
    val ratings=data.map { line =>
      val arr=line.split("\t")
      val userId=arr(0).toInt
      val movieId=arr(1).toInt
      val score=arr(2).toDouble
      
      Rating(userId,movieId,score)
    }
    //④参:λ 防止模型过拟合。可以不引入。如果引入也是一个很小的值
    //随机化和结果的多样性也是推荐系统的特点。这也是由底层的算法性质决定的
    val model=ALS.train(ratings,50,10,0.01)
    
    val u789Results=model.recommendProducts(789, 10)
  }
  
}