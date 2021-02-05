package cn.tedu.als

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.ALS

/**
 * 处理data目录下的als.txt
 * 用户id  物品id  评分
 * 1 11 2

 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("als")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/als.txt")
    
    //第一步:为了满足建模需要,RDD[line:String]->RDD[Rating(userId:Int,itemId:Int,score:Double)]
    val ratings=data.map { line =>
        val arr=line.split(" ")
        //用户id
        val userId=arr(0).toInt
        //商品id
        val itemId=arr(1).toInt
        //评分
        val score=arr(2).toDouble
        
        Rating(userId,itemId,score)
    }
    
    //建立推荐系统模型,底层使用ALS算法来接
    //①参:数据集  ②参:k的大小。k小于U和I的数量 ③参:最大的迭代次数
    val model=ALS.train(ratings,3,10)
    
    //①参:用户id  ②参:推荐的物品数量。下面表示为2号用户推荐3个商品
    val r1=model.recommendProducts(2, 3)
    
    //①参:物品id  ②参:推荐的用户数量。下面表示为12号商品推荐两名用户
    val r2=model.recommendUsers(12, 2)
    
    //①参:用户id  ②参:物品id 。下面表示预测3号用户对11号商品的评分
    val r3=model.predict(3, 11)
    
    
  }
}