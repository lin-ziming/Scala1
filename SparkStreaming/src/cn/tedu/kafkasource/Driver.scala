package cn.tedu.kafkasource

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import cn.tedu.pojo.LogBean
import cn.tedu.dao.HBaseUtil
import java.util.Calendar
import cn.tedu.pojo.TongjiBean
import cn.tedu.dao.MysqlUtil

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
        
        //第三步：统计业务指标。需要统计pv uv vv newip newcust
        //3.1 pv:用户访问一次就算作一个pv，当前每循环一次，则pv=1
        val pv=1
        
        //3.2 uv:独立访客数。uv=1 或  uv=0
        //判断逻辑:根据当前记录的uvid,去HBase表查询当天的记录，
        //如果此uvid已经出现过，则uv=0 反之uv=1
        //难点1:如何查询HBase当天的数据。
        //查询的startTime=当天凌晨零点的时间戳 
        //查询的endTime=当前记录的sstime
        
        val endTime=sstime.toLong
        
        val calendar=Calendar.getInstance
        calendar.setTimeInMillis(endTime)
        calendar.set(Calendar.HOUR,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        
        //获取当前的凌晨零点的时间戳
        val startTime=calendar.getTimeInMillis
        
        //难点2:如果判断uvid在当天的hbase表是否出现过
        //可以根据正则表达式，结合hbase的行键过滤器来实现
        val uvRegex="^\\d+_"+uvid+".*$"
        
        val uvResult=HBaseUtil.queryHBase(sc,startTime,endTime,uvRegex)
        
        val uv=if(uvResult.count()==0) 1 else 0
        
        //3.3 vv:独立会话数。vv=1 或  vv=0。 根据当条记录中的ssid,去HBase表查询当天数据。
        //如果此ssid在当天的记录中已经出现过，则vv=0,反之vv=1
        
        val vvRegex="^\\d+_\\d+_"+ssid+".*$"
        
        val vvResult=HBaseUtil.queryHBase(sc, startTime, endTime, vvRegex)
        
        val vv=if(vvResult.count()==0) 1 else 0
        
        //3.4 newip:新增ip数。newip=1 或  newip=0。 根据当条记录中的cip，去HBase查询历史数据。
        //如果此cip在历史数据中从未出现过，则newip=1,反之为0
        val ipRegex="^\\d+_\\d+_\\d+_"+cip+".*$"
        
        val ipResult=HBaseUtil.queryHBase(sc, 0, endTime, ipRegex)
        
        val newip=if(ipResult.count()==0) 1 else 0
        
        //3.5 newcust:新增用户数。思路同newip，根据当条记录中的uvid,去HBase查询历史数据
        val custResult=HBaseUtil.queryHBase(sc, 0, endTime, uvRegex)
        
        val newcust=if(custResult==0) 1 else 0
        
        //打印到控制台，测试结果
        //println("pv:"+pv+"uv:"+uv+"vv:"+vv)
        
        //第四步：将统计出的业务指标(pv,uv,...)封装到bean中，然后更新到mysql数据库中
        val tongjiBean=TongjiBean(sstime,pv,uv,vv,newip,newcust)
        
        MysqlUtil.saveToMysql(tongjiBean)
        
        //将bean数据存储到HBase表中,ctrl+1
        HBaseUtil.saveToHBase(sc,logBean) 
      } 
    }
    
    ssc.start()
    
    ssc.awaitTermination()
    
  }
}