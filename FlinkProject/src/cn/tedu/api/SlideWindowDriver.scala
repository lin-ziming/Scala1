package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object SlideWindowDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap(line=>line.split(" "))
                     .map (word=>(word,1))
                     .keyBy(0)
                     //下面表示每隔5s重新计算最近10s的窗口数据
                     .timeWindow(Time.seconds(10),Time.seconds(5))
                     .sum(1)
    
    result.print()
    
    senv.execute()
  }  
}
