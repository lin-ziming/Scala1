package cn.tedu.input

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import java.util.Properties
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala._

/**
 * 学习Flink的Kafka数据源
 */
object KafkaDriver {
  
  def main(args: Array[String]): Unit = {
    
    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    
     val properties = new Properties()
     //指定kafka集群地址列表
     properties.setProperty("bootstrap.servers", "hadoop01:9092,hadoop02:9092,hadoop03:9092")
     //指定zookeeper集群地址
     properties.setProperty("zookeeper.connect", "hadoop01:2181,hadoop02:2181,hadoop03:2181")
     //执行消费组名
     properties.setProperty("group.id", "test")
     
     //①参:消费的主题名  ②参:消费的数据类型是普通的文本类型  ③参:property属性对象
     val counsumer=new FlinkKafkaConsumer[String]("enbook", new SimpleStringSchema(), properties);
    
     //添加kafka数据源,从kafka消费数据
     val source=senv.addSource(counsumer)
     
     source.print()
     
     senv.execute()



  }
}