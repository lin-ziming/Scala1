package cn.tedu.spark.hbase

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.fs.shell.find.Result
import org.apache.hadoop.hbase.client.Put
/**
 * 通过Spark将数据写出到指定的HBase表中
 */
object WriteDriver {
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("writeHBase")
    val sc=new SparkContext(conf)
    
    //指定zookeeper IP地址
    sc.hadoopConfiguration.set("hbase.zookeeper.quorum",
                               "hadoop01,hadoop02,hadoop03")
                               
    sc.hadoopConfiguration.set("hbase.zookeeper.property.clientPort", "2181")                           
    
    //指定输出的HBase表名
    sc.hadoopConfiguration.set(TableOutputFormat.OUTPUT_TABLE, "tbs")
    
    val job=new Job(sc.hadoopConfiguration)
    
    //Immutable:不可变的，Writable:可写的，可序列化的
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    
    //org.apache.hadoop.fs.shell.find.Result
    job.setOutputValueClass(classOf[Result])
    
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    
    val r1=sc.parallelize(List("1 tom 23","2 rose 18","3 jim 25","4 jary 30"))
    
    //为了将RDD数据插入到HBase表，需要做类型的转换
    //RDD[line:String]->RDD[(输出key，输出value)]
    val hbaseRDD=r1.map { line => 
      val arr=line.split(" ")
      val id=arr(0)
      val name=arr(1)
      val age=arr(2)
      
      //org.apache.hadoop.hbase.client.Put
      //创建了一个HBase行对象并指定行键
      val row=new Put(id.getBytes)
      
      //①参：列族名     ②参：列名      ③参：列值
      row.add("cf1".getBytes, "name".getBytes, name.getBytes)
      row.add("cf1".getBytes, "age".getBytes, age.getBytes)
      
      (new ImmutableBytesWritable,row)
    }
    
    //执行写出
    hbaseRDD.saveAsNewAPIHadoopDataset(job.getConfiguration)
    
  }
}