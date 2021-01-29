/**
scala常用的集合(Collection)类型:Array,List,Set,Map,Tuple,Range,Iterate

*/
object Demo03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //创建一个定长数组并赋值。定长指的是一经声明,长度不能改变(不能追加新元素)
  val a1=Array(1,2,3,4)                           //> a1  : Array[Int] = Array(1, 2, 3, 4)
  
  //创建一个定长数组并指定了长度。
  val a2=new Array[Int](3)                        //> a2  : Array[Int] = Array(0, 0, 0)
  
  //创建一个变长数组并赋值,可以追加新元素
  val a3=scala.collection.mutable.ArrayBuffer(1,2,3)
                                                  //> a3  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
  
  //通过下标操作Array,下标从0开始。apply可以省略
  a1.apply(0)                                     //> res0: Int = 1
  a1(0)                                           //> res1: Int = 1
  a1(0)=10
  a1                                              //> res2: Array[Int] = Array(10, 2, 3, 4)
 
 
 	val a4=Array(1,2,3,4,5,6)                 //> a4  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
 	
 	//第一次循环 i=0
 	//第一次循环 i=2
 	//第三次循环 i=4
 	for(i<-0.until(a4.length,2)){
 			var tmp=a4(i)
 			a4(i)=a4(i+1)
 			a4(i+1)=tmp
 	
 	}
 	
 	a4                                        //> res3: Array[Int] = Array(2, 1, 4, 3, 6, 5)
 	
 	//对变长数组追加新元素
 	a3.append(4,5,6)
  
  
  val a5=Array(2,1,7,4,3,6)                       //> a5  : Array[Int] = Array(2, 1, 7, 4, 3, 6)
  
  //获取最大值
  val r1=a5.max                                   //> r1  : Int = 7
  //获取最小值
  val r2=a5.min                                   //> r2  : Int = 1
  //求和
  val r3=a5.sum                                   //> r3  : Int = 23
  
  //练习1:求出a5的均值
  val r4=a5.sum.toDouble/a5.length                //> r4  : Double = 3.8333333333333335
  
  //取出前n个元素,并返回到一个新的集合中
  val r5=a5.take(3)                               //> r5  : Array[Int] = Array(2, 1, 7)
  //取出后n个元素,并返回到一个新的集合中
  val r6=a5.takeRight(2)                          //> r6  : Array[Int] = Array(3, 6)
  //去除前n个元素,将剩余元素返回到新的集合中
  val r7=a5.drop(2)                               //> r7  : Array[Int] = Array(7, 4, 3, 6)
  //去除后n个元素,将剩余元素返回到新的集合中
  val r8=a5.dropRight(2)                          //> r8  : Array[Int] = Array(2, 1, 7, 4)
  
  //练习2:计算a5中间项的极差(最大值-最小值)
  val r9=a5.drop(1).dropRight(1)                  //> r9  : Array[Int] = Array(1, 7, 4, 3)
  val r10=r9.max-r9.min                           //> r10  : Int = 6
  
  val a6=Array(1,2,3)                             //> a6  : Array[Int] = Array(1, 2, 3)
  val a7=Array(3,4,5)                             //> a7  : Array[Int] = Array(3, 4, 5)
  //交集。返回相同的数据到新的集合中
  val r11=a6.intersect(a7)                        //> r11  : Array[Int] = Array(3)
  //并集
  val r12=a6.union(a7)                            //> r12  : Array[Int] = Array(1, 2, 3, 3, 4, 5)
  //差集,注意方向性
  val r13=a6.diff(a7)                             //> r13  : Array[Int] = Array(1, 2)
  val r14=a7.diff(a6)                             //> r14  : Array[Int] = Array(4, 5)
  //去重，将去重后的结果返回到新的集合中
  val r15=r12.distinct                            //> r15  : Array[Int] = Array(1, 2, 3, 4, 5)
  
  val a8=Array(2,1,3,4)                           //> a8  : Array[Int] = Array(2, 1, 3, 4)
  //翻转,将翻转后的结果返回到新的集合中
  val r16=a8.reverse                              //> r16  : Array[Int] = Array(4, 3, 1, 2)
  
  //将集合中的所有数据返回为一个字符串,并指定分隔符
  val r17=a8.mkString                             //> r17  : String = 2134
  val r18=a8.mkString(",")                        //> r18  : String = 2,1,3,4
  
  
  //filter:根据传入的匿名函数实现过滤。将过滤后的数据返回到新集合中
  val a9=Array(1,2,3,4,5,6)                       //> a9  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
  
  //练习3:基于a9,过滤出大于3的偶数数据
  val r19=a9.filter{(num:Int)=>{num>3&&num%2==0}} //> r19  : Array[Int] = Array(4, 6)
  
  val r20=a9.filter{num=>num>3&&num%2==0}         //> r20  : Array[Int] = Array(4, 6)
  
  val a10=Array("tom M 23","rose F 18","jary M 25")
                                                  //> a10  : Array[String] = Array(tom M 23, rose F 18, jary M 25)
  
  //练习4:基于a10,过滤出男性数据
  val r21=a10.filter { line => line.split(" ")(1)=="M" }
                                                  //> r21  : Array[String] = Array(tom M 23, jary M 25)
  //练习5:基于a10,过滤出年龄大于18岁的数据
  val r22=a10.filter { line =>line.split(" ")(2).toInt>18  }
                                                  //> r22  : Array[String] = Array(tom M 23, jary M 25)
}