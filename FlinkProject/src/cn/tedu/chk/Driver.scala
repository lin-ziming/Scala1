package cn.tedu.chk

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.api.scala._

/**
 * flink默认的快照管理器用的是MemoryStateBackend。即默认是将checkpoint存到内存中。
 * 好处是当恢复数据时，速度更快。弊端在于在生产环境下,数据巨大,有可能会造成内存溢出的风险。
 * 并且内存并不总是可靠的，存在断电丢失的情况。
 * 
 * 所以可以使用FsStateBackend快照管理器。可以将checkpoint存到磁盘文件上
 * 
 */
object Driver {
 
  def main(args: Array[String]): Unit = {
    
     val env= StreamExecutionEnvironment.getExecutionEnvironment
     
     env.enableCheckpointing(3000)
     
     env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
     
     //hdfs://hadoop01:9000/chk
     env.setStateBackend(new FsStateBackend("file:///E://flink-check"))
     
     val source=env.socketTextStream("hadoop01",8888)
     
     val result=source.flatMap(line=>line.split(" "))
                      .map { word => (word,1) }
                      .keyBy(0)
                      .sum(1)

    result.print()
    
    env.execute()


  }
}