package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import java.util.concurrent.TimeUnit

object KeyByAndBatchDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap(line=>line.split(" "))
                     .map(word => (word,1))
                     .keyBy(0)
                     .timeWindow(Time.of(5,TimeUnit.SECONDS))//指定每5s一次批次进行统计
                     .sum(1)
    
    result.print()
    
    senv.execute()
  }  
}
