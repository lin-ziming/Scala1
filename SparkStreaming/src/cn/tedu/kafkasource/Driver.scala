package cn.tedu.kafkasource

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import cn.tedu.pojo.LogBean
import cn.tedu.dao.HBaseUtil

object Driver {
  def main(args: Array[String]): Unit = {
    
    //local[5]中的数字表示启动的线程数。如果不指定，默认只启动一个线程。
    //SparkStreaming从Kafka消费数据时，启动的线程数至少是两个
    //其中一个线程负责SparkStreaming,另外一个线程负责从Kafka消费
    //若只有一个线程，则SparkStreaming可以启动，但无法消费数据
    val conf=new SparkConf().setMaster("local[5]").setAppName("kafkasource")
    
    val sc=new SparkContext(conf)
    
    val ssc=new StreamingContext(sc,Seconds(5))
    
    //指定zookeeper集群地址。注意主机名与对应的ip的映射是否正确。C:\Windows\System32\drivers\etc
    val zkHosts="hadoop01:2181,hadoop02:2181,hadoop03:2181"
    //执行消费者组名。组内竞争消费,组间共享消费
    val groupId="gp1"
    
    //指定消费的主题信息。key是主题名,value是消费的线程数,至少是1个线程
    //也可以消费多个主题，比如：Map("logdata"->1,"enbook"->2)
    val topics=Map("logdata"->1)

    //通过SparkStreaming整合包提供的工具类，从kafka获取数据
    val kafkaSource=KafkaUtils.createStream(ssc, zkHosts, groupId, topics)
                              .map{x=>x._2}
    
    //foreachRDD是DStream的输出方法。将每一批次的DStream直接转为RDD进行操作
    //后续用户可以直接对RDD进行处理，最后输出到指定的应用系统。比如存到HBase或Mysql等
    kafkaSource.foreachRDD{rdd=>
      
      //将当前批次数据的RDD转变为迭代器
      val lines=rdd.toLocalIterator
      
      //遍历迭代器,获取每条数据进行处理
      while(lines.hasNext){
        //获取一条数据
        val line=lines.next()
        //println(line)
        
        //第一步:做字段清洗。需要的字段:url  urlname uvid ssid sscount sstime cip
        val arr=line.split("\\|")
        val url=arr(0)
        val urlname=arr(1)
        val uvid=arr(13)
        val ssid=arr(14).split("_")(0)
        val sscount=arr(14).split("_")(1)
        val sstime=arr(14).split("_")(2)
        val cip=arr(15)
        
        //第二步:将清洗出的字段封装到bean中
        val logBean=LogBean(url,urlname,uvid,ssid,sscount,sstime,cip)
        
        //将bean数据存储到HBase表中,ctrl+1
        HBaseUtil.saveToHBase(sc,logBean)
        
      }
      
    }
    
    ssc.start()
    
    ssc.awaitTermination()
    
  }
}