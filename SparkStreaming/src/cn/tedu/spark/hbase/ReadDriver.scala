package cn.tedu.spark.hbase

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Result

/**
 * Spark从HBase表读数据
 */
object ReadDriver {
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("readHBase")
    val sc=new SparkContext(conf)
    
    val hbaseConf=HBaseConfiguration.create()
    
    hbaseConf.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03")
    
    hbaseConf.set("hbase.zookeeper.property.clientPort", "2181")
    //指定读取的表名
    hbaseConf.set(TableInputFormat.INPUT_TABLE, "tbs")
    
    //执行读取,将指定HBase表中的数据读取到结果集RDD中
    //org.apache.hadoop.hbase.client.Result
    val resultRDD=sc.newAPIHadoopRDD(hbaseConf, 
                                    classOf[TableInputFormat], 
                                    classOf[ImmutableBytesWritable], 
                                    classOf[Result])
    
    resultRDD.foreach{case(key,value)=>
      
      //①参：列族名  ②参：列名
      val name=value.getValue("cf1".getBytes,"name".getBytes)
      val age=value.getValue("cf1".getBytes, "age".getBytes)
      
      println(new String(name)+":"+new String(age))
    }
    
                                    
  }
}