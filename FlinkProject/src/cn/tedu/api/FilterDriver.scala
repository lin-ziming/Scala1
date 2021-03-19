package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

object FilterDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.filter{line=>line.split(" ")(1)=="M"}
    
    result.print()
    
    senv.execute()
  }  
}
