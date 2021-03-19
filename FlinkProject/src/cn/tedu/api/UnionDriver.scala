package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

object UnionDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source01=senv.socketTextStream("hadoop01", 8888)
    val source02=senv.socketTextStream("hadoop01", 8889)
    val source03=senv.socketTextStream("hadoop01", 8890)
    
    
    val result=source01.union(source02,source03)
    
    result.print()
    
    senv.execute()
  }  
}
