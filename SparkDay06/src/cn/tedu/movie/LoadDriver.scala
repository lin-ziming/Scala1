package cn.tedu.movie

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object LoadDriver {
  
  def cosArr(a:Array[Double],b:Array[Double])={
    val ab=a zip b
    val fenzi=ab.map(x=>x._1*x._2).sum
    val fenmuA=Math.sqrt(a.map { x => x*x }.sum)
    val fenmuB=Math.sqrt(b.map{x=>x*x}.sum)
    fenzi/(fenmuA*fenmuB)
  }
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("load")
    val sc=new SparkContext(conf)
    
    val model=MatrixFactorizationModel.load(sc, "d://2008scala/sparkTest/rec-result")
    
    //完成基于物品推荐
    //获取用户因子矩阵
    val userFactors=model.userFeatures
    
    //获取物品因子矩阵。返回的RDD[物品id：Int,物品的因子数组:Array[Double]]
    val movieFactors=model.productFeatures
    
    //movieFactors.foreach{case(id,arr) =>println(id+":"+arr.mkString(",")) }
    
    val movie123Factor=movieFactors.filter{case(id,arr)=>id==123}.first._2
    
    movie123Factor.foreach(println)
    
    val cosReuslts=movieFactors.map{case(id,factor)=>
      val cos=cosArr(factor, movie123Factor)
      //返回当前的电影id，当前电影与123号电影的相似度
      (id,cos)
    }
    
    val top10=cosReuslts.sortBy{x=> -x._2}.take(11).drop(1)
    top10.foreach{println}
    
  }
}