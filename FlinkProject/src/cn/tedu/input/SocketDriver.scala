package cn.tedu.input

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * 学习Flink的Socket数据源
 */
object SocketDriver {
  
  def main(args: Array[String]): Unit = {
    //获取flink的实时流计算的执行环境
    val senv= StreamExecutionEnvironment.getExecutionEnvironment
    
    //指定socket数据源。先启动nc,再启动flink
    val source=senv.socketTextStream("hadoop01",8888)
    
    //输出到控制台
    source.print()
    
    //启动flink
    senv.execute()
    
  }
}