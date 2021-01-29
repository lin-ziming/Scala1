/**
知识点
scala的函数分为如下几种:
1.成员函数,定义在类的内部,做为类的一个成员。称为成员函数
2.本地函数,定义在函数内的函数,称为本地函数
3.匿名函数:①没有函数名  ②参数列表与{}之间使用=> 来连接
关键是匿名函数的作用:
①作为参数赋值  ②作为参数传递  ③作为返回值返回
4.高阶函数,将函数作为参数传递的函数,是高阶函数
5.匿名函数和高阶函数都是来自于面向函数的编程。即scala既是面向对象的语言,也是面向函数的语言。
面向函数的语言,函数是一等公民。函数具备完整的能力,可以把函数作为参数传递、赋值、作为返回值返回等
6.scala通用的化简规则:
①方法体{}只有一行代码，则{}可以省略
②调用方法,方法只有一个参数, 则 .()可以省略
③在传入匿名函数时,匿名函数形参的类型可以省略
④在传入匿名函数时,如果匿名函数的参数只有一个,则匿名函数参数列表的()可以省略
⑤终极化简:可以使用_ 代替匿名函数的参数进行化简


*/
object Demo07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  def f1(a:Int,b:Int)={a+b}                       //> f1: (a: Int, b: Int)Int
  
  //声明一个匿名函数
  (a:Int,b:Int)=>{a+b}                            //> res0: (Int, Int) => Int = <function2>
  
  //把匿名函数作为参数赋值给f2。
  val f2=(a:Int,b:Int)=>{a+b}                     //> f2  : (Int, Int) => Int = <function2>
  
  f2(2,3)                                         //> res1: Int = 5
  
  //定义一个高阶函数
  //把匿名函数作为参数传递，以及做为返回值返回
  def f3(a:Int,b:Int,f:(Int,Int)=>Int)={
  		f(a,b)
  }                                               //> f3: (a: Int, b: Int, f: (Int, Int) => Int)Int
  
  f3(2,3,(a:Int,b:Int)=>{a*b})                    //> res2: Int = 6
  f3(2,3,(a,b)=>a*b)                              //> res3: Int = 6
  f3(2,3,_*_)                                     //> res4: Int = 6
  
  //练习1:根据调用,定义出对应的高阶函数
  def f4(a:String,b:Int,f:(String,Int)=>String)={
  		f(a,b)
  }                                               //> f4: (a: String, b: Int, f: (String, Int) => String)String
  
  f4("hello",3,(a:String,b:Int)=>{a.*(b)})        //> res5: String = hellohellohello
  f4("hello",3,(a,b)=>a.*(b))                     //> res6: String = hellohellohello
  f4("hello",3,_*_)                               //> res7: String = hellohellohello
  
  //练习2:
  def f5(a:Int,b:Int,f:(Int,Int)=>Int)={
  	f(a,b)
  }                                               //> f5: (a: Int, b: Int, f: (Int, Int) => Int)Int
  f5(10,5,(a:Int,b:Int)=>{if(a>b)a else b})       //> res8: Int = 10
  f5(10,5,(a,b)=>if(a>b)a else b)                 //> res9: Int = 10
  
  //练习3:
  def f6(a:String,f:(String)=>String)={
  	f(a)
  }                                               //> f6: (a: String, f: String => String)String
  f6("file01.txt",(a:String)=>a.takeRight(4))     //> res10: String = .txt
  f6("file01.txt",(a)=>a.takeRight(4))            //> res11: String = .txt
  
  f6("file01.txt",a=>a.takeRight(4))              //> res12: String = .txt
  f6("file01.txt",_.takeRight(4))                 //> res13: String = .txt
  
  
  //练习4:
  def f7(a:String,f:(String)=>Array[String])={
  		f(a)
  }                                               //> f7: (a: String, f: String => Array[String])Array[String]
  f7("hello world",(a:String)=>{a.split(" ")})    //> res14: Array[String] = Array(hello, world)
  f7("hello world",(a)=>a.split(" "))             //> res15: Array[String] = Array(hello, world)
  f7("hello world",a=>a.split(" "))               //> res16: Array[String] = Array(hello, world)
  f7("hello world",_.split(" "))                  //> res17: Array[String] = Array(hello, world)
  
  
  val a1=Array(1,2,3,4,5,6)                       //> a1  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
  
  //filter是scala集合提供的高阶函数。根据传入的匿名函数,实现过滤。并将过滤后的数据返回到新的集合
  //过滤出a1大于3的所有数据
  val a2=a1.filter {(a:Int) =>{a>3} }             //> a2  : Array[Int] = Array(4, 5, 6)
  val a3=a1.filter {(a) =>a>3 }                   //> a3  : Array[Int] = Array(4, 5, 6)
  val a4=a1.filter {a=>a>3 }                      //> a4  : Array[Int] = Array(4, 5, 6)
  val a5=a1.filter {_>3 }                         //> a5  : Array[Int] = Array(4, 5, 6)
}