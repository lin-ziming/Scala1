package cn.tedu.spark.hbase

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Base64

/**
 * Spark从HBase表读数据
 */
object ScanDriver {
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("readHBase")
    val sc=new SparkContext(conf)
    
    val hbaseConf=HBaseConfiguration.create()
    
    hbaseConf.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03")
    
    hbaseConf.set("hbase.zookeeper.property.clientPort", "2181")
    //指定读取的表名
    hbaseConf.set(TableInputFormat.INPUT_TABLE, "tbs")
    
    //通过行键读取HBase指定范围的数据
    //创建HBase表的扫描对象
    //import org.apache.hadoop.hbase.client.Scan
    val scan=new Scan()
    //指定查询的起始行键
    scan.setStartRow("2".getBytes)
    //指定查询的终止行键,含头不含尾
    scan.setStopRow("4".getBytes)
    
    //设置scan对象使其生效
    //import org.apache.hadoop.hbase.protobuf.ProtobufUtil
    //import org.apache.hadoop.hbase.util.Base64
    hbaseConf.set(TableInputFormat.SCAN, 
            Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray()) )
    
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