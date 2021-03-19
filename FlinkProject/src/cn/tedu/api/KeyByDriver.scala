package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

object KeyByDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap(line=>line.split(" "))
                     .map(word => (word,1))
                     //.keyBy{x=>x._1}//表示以元组的第一个元素为key进行分组
                     .keyBy(0)//0表示按key分组。
                     .sum(1)//表示将value进行求和。0表示按key求和,1表示按value求和
    
    result.print()
    
    senv.execute()
  }  
}
