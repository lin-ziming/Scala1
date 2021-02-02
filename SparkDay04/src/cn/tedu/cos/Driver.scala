package cn.tedu.cos

/**
 * 通过scala代码计算两个向量之间的夹角余弦
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val a=Array(1,2,3)
    val b=Array(5,10,3)
    
    //提示1:Math.sqrt()
    //提示2:可以使用拉链方法  zip。   两个集合可以使用此方法，比如 a.zip(b) 
    
    val ab=a.zip(b)
    
    val fenzi=ab.map(x=>x._1*x._2).sum
    
    val fenmuA=Math.sqrt(a.map { x => x*x }.sum)
    val fenmuB=Math.sqrt(b.map { x => x*x }.sum)
    
    val cos=fenzi/(fenmuA*fenmuB)
    
    println(cos)
  }
}