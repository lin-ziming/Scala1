package cn.tedu.dao

import cn.tedu.pojo.TongjiBean
import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection


object MysqlUtil {
  
  val c3p0=new ComboPooledDataSource
  
  def saveToMysql(tongjiBean: TongjiBean) = {
    //写在外面是为了能在finally中关闭连接
    var conn:Connection=null
    
    try {
      conn=c3p0.getConnection
      
    } catch {
      case t: Throwable => t.printStackTrace() // TODO: handle error
    }finally {
      
    }
    
  }
}