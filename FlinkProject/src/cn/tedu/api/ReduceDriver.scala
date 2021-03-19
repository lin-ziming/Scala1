package cn.tedu.api

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

object ReduceDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
    val source=senv.socketTextStream("hadoop01", 8888)
    
    
    val result=source.flatMap(line=>line.split(" "))
                     .map(word=>(word,1))
                     .keyBy(0)
                     //(hello,1) (hello,1)-->(hello,2)
                     //(hello,2) (hello,1)-->(hello,3)
                     //...                   
                     .reduce((a,b)=>(a._1,a._2+b._2))
    
    result.print()
    
    senv.execute()
  }  
}
