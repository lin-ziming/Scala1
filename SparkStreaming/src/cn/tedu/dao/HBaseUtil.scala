package cn.tedu.dao

import org.apache.spark.SparkContext
import cn.tedu.pojo.LogBean
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.client.Put
import scala.util.Random
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.fs.shell.find.Result
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.RowFilter
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.filter.RegexStringComparator
import org.apache.hadoop.hbase.util.Base64
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.client.Result

object HBaseUtil {
  def saveToHBase(sc: SparkContext, logBean: LogBean) = {
    
    sc.hadoopConfiguration.set("hbase.zookeeper.quorum", 
                               "hadoop01,hadoop02,hadoop03")
    sc.hadoopConfiguration.set("hbase.zookeeper.property.clientPort","2181")
    sc.hadoopConfiguration.set(TableOutputFormat.OUTPUT_TABLE,"logtb")
    
    //org.apache.hadoop.mapreduce.Job
    val job=new Job(sc.hadoopConfiguration)
    
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    //org.apache.hadoop.fs.shell.find.Result
    job.setOutputValueClass(classOf[org.apache.hadoop.fs.shell.find.Result])
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    
    val r1=sc.parallelize(List(logBean))
    
    val hbaseRDD=r1.map { bean => 
      
      //创建HBase行对象并指定行键。导包:org.apache.hadoop.hbase.client.Put
      //本项目行键设计为：sstime_uvid_ssid_cip_随机数字
      //行键以时间戳开头，因为HBase会对行键做升序排序，即达到了按时间戳升序排序的效果，所以便于后续按时间段做范围查询
      //此外，行键中还包含uvid,ssid,cip信息，为了便于后续统计相关指标，uv,vv,newip等
      //最后拼接随机数字，满足散列原则
      
      //scala.util.Random
      val rowKey=bean.sstime+"_"+bean.uvid+"_"+bean.ssid+"_"+bean.cip+"_"+Random.nextInt(100)
      val row=new Put(rowKey.getBytes)
      
      row.add("cf1".getBytes, "url".getBytes, bean.url.getBytes)
      row.add("cf1".getBytes, "urlname".getBytes, bean.urlname.getBytes)
      row.add("cf1".getBytes, "uvid".getBytes, bean.uvid.getBytes)
      row.add("cf1".getBytes, "ssid".getBytes, bean.ssid.getBytes)
      row.add("cf1".getBytes, "sscount".getBytes, bean.sscount.getBytes)
      row.add("cf1".getBytes, "sstime".getBytes, bean.sstime.getBytes)
      row.add("cf1".getBytes, "cip".getBytes, bean.cip.getBytes)
      
      (new ImmutableBytesWritable,row)
    }
    
    //执行写出
    hbaseRDD.saveAsNewAPIHadoopDataset(job.getConfiguration)
    
  }

  def queryHBase(sc: SparkContext, startTime: Long, endTime: Long, regex: String) = {
  
    val hbaseConf=HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03")
    hbaseConf.set("hbase.zookeeper.property.clientPort", "2181")
    hbaseConf.set(TableInputFormat.INPUT_TABLE, "logtb")
    
    val scan=new Scan()
    
    scan.setStartRow(startTime.toString().getBytes)
    scan.setStopRow(endTime.toString().getBytes)
    
    val filter=new RowFilter(CompareOp.EQUAL,new RegexStringComparator(regex))
    //绑定过滤器使其生效
    scan.setFilter(filter)
    
    //设置scan对象
    //import org.apache.hadoop.hbase.protobuf.ProtobufUtil
    //import org.apache.hadoop.hbase.util.Base64
    hbaseConf.set(TableInputFormat.SCAN, 
            Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray()) )
            
    val resultRDD=sc.newAPIHadoopRDD(hbaseConf, 
                            classOf[TableInputFormat], 
                            classOf[ImmutableBytesWritable], 
                            classOf[org.apache.hadoop.hbase.client.Result])
    
    resultRDD
  }
}