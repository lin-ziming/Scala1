package cn.tedu.sql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("sql")
    
    val sc=new SparkContext(conf)
    
    val sqc=new SQLContext(sc)
    
    val data=sc.textFile("e://data/MaxMin.txt")
    
    //RDD[String]->RDD[(id,gender,height)]
    val r1=data.map { line=>line.split(" ") }
               .map { arr =>(arr(0).toInt,arr(1),arr(2).toInt) }
    
    //导入sqc的隐式转换,相当于省略 sqc.createDataFrame()           
    import sqc.implicits._           
    val df1=r1.toDF("id","gender","height")      
    
    //DataFrame的show方法用于将表数据打印到控制台。最多只显示前20条
    df1.show
    
    //通过方法的形式操作DataFrame
    val result01=df1.select("id","height")
    
    //通过sql的形式操作DataFrame
    df1.registerTempTable("tb1")
    
    val result02=sqc.sql("select id,height from tb1")
    
    val result02RDD=result02.toJavaRDD
    
    result02RDD.saveAsTextFile("e://data/sql-result")
    
    
  }
}