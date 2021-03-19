package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object CountWindowDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap(line=>line.split(" "))
                     .map (word=>(word,1))
                     .keyBy(0)
                     //计数窗口,当key满足指定数量要求时，才会触发计算
                     .countWindow(4)
                     .sum(1)
    
    result.print()
    
    senv.execute()
  }  
}
