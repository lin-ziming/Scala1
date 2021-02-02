package cn.tedu.median

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 求出median.txt 中一组数据的中位数
 * 
 * 比如:
 * 5 1 3 7 9
 * 1 3 5 7 9 中位数:5
 * 
 * 中位数的位置=(n+1)/2=3
 * 
 * 1 3 5 7 9 11 中位数:6
 * 
 * 只需考虑元素个数是奇数个情况。正确结果是10
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("maxmin")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/median.txt")
    
    val r1=data.flatMap { line => line.split(" ") }
               .map { num => num.toInt }
               
    val r2=r1.sortBy{num=>num} 
    
    //获取中位数位置
    val medianPos=(r2.count+1)/2
    
    //val median=r2.take(medianPos.toInt).last
    
    //RDD无法直接通过下标操作。可以先转成Array然后再进行操作
    val median=r2.collect()(medianPos.toInt-1)
    
    println(median)
  }
}