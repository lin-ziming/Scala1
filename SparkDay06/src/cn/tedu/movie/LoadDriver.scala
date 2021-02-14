package cn.tedu.movie

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object LoadDriver {
  
  
  def cosArr(a:Array[Double],b:Array[Double])={
    
    val ab=a zip b
    
    val fenzi=ab.map{x=>x._1*x._2}.sum
    val fenmuA=Math.sqrt(a.map { x => x*x }.sum)
    val fenmuB=Math.sqrt(b.map { x => x*x }.sum)
    
    fenzi/(fenmuA*fenmuB)
    
  }
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("load")
    
    val sc=new SparkContext(conf)
    
    //加载推荐系统模型
    val model=MatrixFactorizationModel.load(sc,"e://data/rec-result")
    
    //完成基于物品的推荐。比如某用户看了一部123号电影,我们要推荐和123号电影最相似的10部电影
    //实现思路:
    //1.因为要计算物品与物品之间的相似度，所以需要获取模型的物品因子矩阵
    //2.获取123号电影的因子数组
    //3.计算其他所有电影和123号电影的相似度
    //4.根据计算出的相似度做降序排序
    //5.取出前10部,即取出了和123号电影最相似的前10部电影
    
    
    //获取用户因子矩阵
    val userFactors=model.userFeatures
    
    //获取物品因子矩阵。返回的RDD[(物品id:Int,物品的因子数组:Array[Double])]
    val movieFactors=model.productFeatures
    
    //获取123号电影的因子数组
    val movie123Factor=movieFactors.filter{case(id,arr)=>id==123}.first._2
    
    
    //计算其他所有电影和123号电影的相似度。使用向量间的夹角余弦来计算
    val cosResults=movieFactors.map{case(id,factor)=>
      
      //计算当前电影的factor和 123号电影 factor的相似度
      val cos=cosArr(factor, movie123Factor)
      
      //返回当前的电影id,当前电影与123号电影的相似度
      (id,cos)
    }
    
    val top10=cosResults.sortBy{x=> -x._2}.take(11).drop(1)
    
    top10.foreach{println}
  }
}