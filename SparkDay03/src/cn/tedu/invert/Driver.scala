package cn.tedu.invert

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * 处理data目录inverted目录下的所有文件，最后输出一个简易的倒排索引表
 * (hello,doc1,doc2)
 * (spark,doc1,doc2)
 * (hbase,doc2,doc3)
 * ...
 */
object Driver {
  def main(args: Array[String]): Unit = {
    val conf =new SparkConf().setMaster("local").setAppName("invert")   
    val sc=new SparkContext(conf)

    //wholeTextFiles:可以将指定目录下的所有文件数据读取到一个RDD中。
    //返回的RDD[(filePath,fileText)]
    val data=sc.wholeTextFiles("D://sparkTest/data/inverted/*")    
    //data.foreach(println)
    
//    val r1=data.map{x=>(x._1.split("/")(5).split("\\.")(0),x._2)}
//    r1.foreach(println)
//    
//    val r2=r1.map{x=>(x._1,x._2.split("\r\n")(0),x._2.split("\n")(1)) }
//    r2.foreach(println)
//    
//    val r3=r2.map{x=>(x._1,x._2.split(" ")(0),x._2.split(" ")(1),x._3.split(" ")(0),x._3.split(" ")(1) ) }
//    r3.foreach(println)
    
    //val r4=r3.
    
    //-------------------------------------------------------------
    //第一步
    val r1=data.map{case(filePath,fileText)=>
      val fileName=filePath.split("/").last.dropRight(4)
      (fileName,fileText)
    }
    r1.foreach{println}
    //第二步
    val r2=r1.flatMap{case(fileName,fileText)=>
      fileText.split("\r\n").flatMap { line => line.split(" ") }.map{word=>(word,fileName)}  
    }
    r2.foreach { println }
    //第三步
    val r3=r2.groupByKey.map{case(word,it)=>(word,it.toList.distinct.mkString(","))}
    r3.foreach{println}
    
    
  }
}