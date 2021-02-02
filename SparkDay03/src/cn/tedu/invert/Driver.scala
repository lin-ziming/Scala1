package cn.tedu.invert

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * 处理data目录inverted目录下的所有文件,最后输出一个简易的倒排索引表
 * (hello,doc1,doc2)
 * (spark,doc1,doc2)
 * (hbase,doc2,doc3)
 * ......
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("invert")
    
    val sc=new SparkContext(conf)
    
    //wholeTextFiles:可以将指定目录下的所有文件数据读取到一个RDD中。
    //返回的RDD[(filePath,fileText)]
    val data=sc.wholeTextFiles("e://data/inverted/*")
    
    //接着往下写代码，完成要求。
    //提示1:文件名可以从filePath来获取
    //提示2:获取单词时,fileText先按\r\n切出行,行再按 空格切出单词
    
    //第一步:RDD[(filePath,fileText)]->RDD[(fileName,fileText)]
    val r1=data.map{case(filePath,fileText)=>
        val fileName=filePath.split("/").last.dropRight(4)   
        (fileName,fileText)
    }
    
    //第二步:RDD[(fileName,fileText)]->RDD[(word,fileName)]
    val r2=r1.flatMap{case(fileName,fileText)=>
        fileText.split("\r\n").flatMap{line=>line.split(" ")}.map{word=>(word,fileName)}
    }
    //第三步:按单词为key进行分组
    val r3=r2.groupByKey.map {case(word,it) =>(word,it.toList.distinct.mkString(",")) }
    
    r3.foreach{println}
    
  }
}