/**
知识点
1.scala通过def关键字定义函数。
形式: def 函数名(形参名:参数类型,...):返回值类型={方法体}
2.scala函数的返回值不需要加return返回。会将方法体{}最后一行代码当做返回值返回
3.scala通用的化简规则:如果方法体只有一行代码,则{}可以省略
4.scala函数的返回值类型可以省略不写。可以根据返回值自动推断出返回值类型
5.需要注意:如果scala函数,参数列表与{}之间没有=,则返回值一律为Unit
6.scala函数支持默认参数机制。例如: def f5(a:String,b:String="world")={}
7.scala函数支持变长参数机制，等价于java的可变参数机制。例如:def f9(a:String*)={}
注意:可变参数必须位于参数列表的最后位置
*/
object Demo06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def f1(a:Int,b:Int):Int={
  	  print("a+b")
  		a+b
  }                                               //> f1: (a: Int, b: Int)Int
  
  f1(2,3)                                         //> a+bres0: Int = 5
  
  
  def f2(a:Int,b:Int)={
  	a+b
  }                                               //> f2: (a: Int, b: Int)Int
  
  def f3(a:String)={
  	a.split(" ")
  }                                               //> f3: (a: String)Array[String]
  
  def f4(a:Int,b:Int){
  	a+b
  }                                               //> f4: (a: Int, b: Int)Unit
  //scala函数支持默认参数机制
  def f5(a:String,b:String="world")={
  	a+b
  }                                               //> f5: (a: String, b: String)String
  
  f5("hello","2008")                              //> res1: String = hello2008
  
  
  //练习1:编写一个函数,提取身份号的后4位
  def f6(a:String)={
  	a.takeRight(4)
  }                                               //> f6: (a: String)String
  
  f6("1231301232139183")                          //> res2: String = 9183
  
  //练习2:编写一个函数，传入两个整型数字，start end。打印出这段范围内的所有数字
  
  def f7(start:Int,end:Int)={
  	for(i<-start to end){
  		println(i)
  	}
  }                                               //> f7: (start: Int, end: Int)Unit
  
  f7(1,5)                                         //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
  //练习3:编写一个函数，传入一个整型数组,打印出数组的所有偶数数据
  def f8(a:Array[Int])={
  	for(i<-a;if i%2==0){
  		println(i)
  	}
  }                                               //> f8: (a: Array[Int])Unit
  
  f8(Array(1,2,3,4))                              //> 2
                                                  //| 4
  
  //scala函数支持变长参数机制。
  def f9(b:Int,a:String*)={
  	a
  }                                               //> f9: (b: Int, a: String*)Seq[String]
  
  f9(100,"hello","world","2008")                  //> res3: Seq[String] = WrappedArray(hello, world, 2008)
}