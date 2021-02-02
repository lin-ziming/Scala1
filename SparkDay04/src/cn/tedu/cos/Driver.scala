package cn.tedu.cos
/**
 * 通过scala代码计算两个向量之间的夹角余弦
 */
object Driver {
  def main(args: Array[String]): Unit = {
    
    val a=Array(1,2,3)
    val b=Array(10,5,3)
    
    val r1=a.zip(b)
//    for(i <= 1 to 3){
//      
//    }
    
    r1.foreach(println)
    println
    val fenzi=r1.map{x=>(x._1*x._2)}.sum
    println(fenzi)
    val fenmuA=Math.sqrt(r1.map{x=>x._1*x._1}.sum)
    val fenmuB=Math.sqrt(r1.map{x=>x._2*x._2}.sum)
    println(fenmuA*fenmuB)
    
    val cos=fenzi/(fenmuA*fenmuB)
    println(cos)
    
  }
}