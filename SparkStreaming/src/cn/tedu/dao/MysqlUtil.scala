package cn.tedu.dao

import cn.tedu.pojo.TongjiBean
import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.Connection
import java.text.SimpleDateFormat
import java.sql.PreparedStatement
import java.sql.ResultSet


object MysqlUtil {
  
  val c3p0=new ComboPooledDataSource
  
  def saveToMysql(tongjiBean: TongjiBean) = {
    //写在外面是为了能在finally中关闭连接
    var conn:Connection=null
    var ps1:PreparedStatement=null
    var rs:ResultSet=null
    var ps2:PreparedStatement=null
    var ps3:PreparedStatement=null
    
    try {
      conn=c3p0.getConnection
      //实现思路：
      //①查询mysql weblog库下的tongji2表
      //②如果当天没有数据，则做新增插入
      //③如果当天已经有数据，则更新累加
      
      val sdf=new SimpleDateFormat("YYY-MM-dd")
      
      //解析出当天的日期，比如2021-02-03
      val todayTime=sdf.format(tongjiBean.sstime.toLong)
      ps1=conn.prepareStatement("select * from tongji2 where reportime=?")
      
      ps1.setString(1, todayTime)

      //执行查询并返回结果集
      rs=ps1.executeQuery()
      if(rs.next()){
        //当天已经有数据，则做更新累加
        ps2=conn.prepareStatement("update tongji2 set pv=pv+?,uv=uv+?,vv=vv+?,newip=newip+?,newcust=newcust+? where reportime=?")
    
        ps2.setInt(1, tongjiBean.pv)
        ps2.setInt(2, tongjiBean.uv)
        ps2.setInt(3, tongjiBean.vv)
        ps2.setInt(4, tongjiBean.newip)
        ps2.setInt(5, tongjiBean.newcust)
        ps2.setString(6,todayTime)
        
        ps2.executeUpdate()
        
      }else {
        //当天还没有数据，则做新增插入
        ps3=conn.prepareStatement("insert into tongji2 values(?,?,?,?,?,?)")
        
        ps3.setString(1,todayTime)
        ps3.setInt(2, tongjiBean.pv)
        ps3.setInt(3, tongjiBean.uv)
        ps3.setInt(4, tongjiBean.vv)
        ps3.setInt(5, tongjiBean.newip)
        ps3.setInt(6, tongjiBean.newcust)
        
        ps3.executeUpdate()
      }
      
    } catch {
      case t: Throwable => t.printStackTrace() // TODO: handle error
    }finally {
      if(ps3!=null)ps3.close        
      if(ps2!=null)ps2.close        
      if(rs!=null)rs.close        
      if(ps1!=null)conn.close        
      if(conn!=null)conn.close
    }
    
  }
}