package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

object FlatMapDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap{line=>line.split(" ")}
                     .map { word =>(word,1) }
    
    result.print()
    
    senv.execute()
    
    
  }  
}
