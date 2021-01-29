/**
学习String相关的方法
*/
object Demo02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  val s="hello world"                             //> s  : String = hello world
  //按指定分隔符切分
  val r1=s.split(" ")                             //> r1  : Array[String] = Array(hello, world)
  //获取长度
  val r2=s.length()                               //> r2  : Int = 11
  //是否包含指定字符
  val r3=s.contains("h")                          //> r3  : Boolean = true
  //将指定字符串重复n次
  val r4=s.*(2)                                   //> r4  : String = hello worldhello world
  
  //返回前n个字符
  val r5=s.take(2)                                //> r5  : String = he
  //返回后n个字符
  val r6=s.takeRight(2)                           //> r6  : String = ld
  //去除前n个字符,并返回剩余字符
  val r7=s.drop(2)                                //> r7  : String = llo world
  //去除后n个字符,并返回剩余字符
  val r8=s.dropRight(2)                           //> r8  : String = hello wor
  
  //练习1:获取手机号的后4位
  val s2="tom1234124231MSH"                       //> s2  : String = tom1234124231MSH
  
  val r9=s2.drop(3).dropRight(3).takeRight(4)     //> r9  : String = 4231
  
  //练习2:获取性别
  val r10=s2.takeRight(3).take(1)                 //> r10  : String = M
  
  val num=1                                       //> num  : Int = 1
  //根据算术运算符优先级计算。优先级同java
  val r11=num+5*3                                 //> r11  : Int = 16
  //根据方法的调用顺序来计算
  val r12=num.+(5).*(3)                           //> r12  : Int = 18
  
  //to方法,用于生成指定的区间集合。
  val r13=1.to(5)                                 //> r13  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
  //until方法,用于生成指定的区间集合。不包含末尾元素
  val r14=1.until(5)                              //> r14  : scala.collection.immutable.Range = Range(1, 2, 3, 4)
  
  //生成区间,并指定步长
  val r15=1.to(5,2)                               //> r15  : scala.collection.immutable.Range.Inclusive = Range(1, 3, 5)
  
  //练习3:生成这样一个区间集合 [5,4,3,2,1]
  val r16=5.to(1,-1)                              //> r16  : scala.collection.immutable.Range.Inclusive = Range(5, 4, 3, 2, 1)
  //reverse 翻转数据
  val r17=1.to(5).reverse                         //> r17  : scala.collection.immutable.Range = Range(5, 4, 3, 2, 1)
  
  
  
}