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
 * 
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
    
    //为789号用户推荐10部电影
    val u789Results=model.recommendProducts(789, 10)
    
    //第一步:将推荐结果中的电影id换为电影名
    //处理思路:
    //1.读取u.item文件,得到:RDD[line:String]
    //2.RDD[line:String]->RDD[(movieId,movieName)]
    //3.RDD[(movieId,movieName)]->collectAsMap->Map(key(id),value(name))
    
    val movieData=sc.textFile("e://data/u.item")
    
    val movieMap=movieData.map { line =>
      val arr=line.split("\\|")  
      val movieId=arr(0).toInt
      val movieName=arr(1)
      
      (movieId,movieName)
    }.collectAsMap
    
    
    val u789=u789Results.map { rt => 
       
       //获取用户id
       val userId=rt.user
       //获取物品id
       val movieId=rt.product
       //获取对应的电影名
       val movieName=movieMap(movieId)
       //获取评分
       val score=rt.rating
       
       (userId,movieName)
    }
    
    //第二步:检验推荐结果的准确性。本例采用直观检验法
    //实现思路:
    //1.获取789号用户看过的所有电影。
    //2.根据789号用户对这些电影的评分,做降序排序
    //3.取出789号用户最喜欢的前10部电影
    //4.用推荐的结果和他喜爱的电影做比较,看是否有相似的
    
    
    val u789Movies=ratings.filter { rt => rt.user==789 }
                          .sortBy{rt=> -rt.rating}
    
    //获取u789号用户最喜欢的前10部电影名                     
    val u789Top10=u789Movies.take(10).map { rt => movieMap(rt.product) }
    
    //第三步:模型的存储,后续如果要使用,则直接加载即可。
    //hdfs://hadoop01:9000/rec-result
    model.save(sc,"e://data/rec-result")
    
  }
  
}