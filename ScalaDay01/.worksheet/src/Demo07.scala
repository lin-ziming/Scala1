/**
知识点
scala的函数分为如下几种：
(1)成员函数：定义在类的内部，作为类的一个成员。称为成员函数
(2)本地函数：定义在函数内的函数，称为本地函数
(3)匿名函数：1.没有函数名 2.参数列表与{}之间使用=> 来连接
关键是匿名函数的作用：
1.作为参数赋值 2.作为参数传递 3.作为返回值返回
(4)高阶函数：将函数作为参数传递的函数，是高阶函数
(5)匿名函数和高阶函数都是来自于面向函数的编程。即scala既是面向对象的语言，也是面向函数的语言。
面向函数的语言，函数是一等公民。函数具备完整的能力，可以把函数作为参数传递、赋值、作为返回值返回等
(6)scala通用的化简规则：

*/
object Demo07 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(366); 
  println("Welcome to the Scala worksheet");$skip(31); 
  
  def f1(a:Int,b:Int)={a+b};System.out.println("""f1: (a: Int, b: Int)Int""");$skip(39); val res$0 = 
  
  //声明一个匿名函数
  (a:Int,b:Int)=>{a+b};System.out.println("""res0: (Int, Int) => Int = """ + $show(res$0));$skip(54); 
  
  //把匿名函数作为参数赋值给f2
  val f2 = (a:Int,b:Int)=>{a+b};System.out.println("""f2  : (Int, Int) => Int = """ + $show(f2 ));$skip(13); val res$1 = 
  
  f2(2,3);System.out.println("""res1: Int = """ + $show(res$1));$skip(74); 
  
  //把匿名函数作为参数传递
  def f3(a:Int,b:Int,f:(Int,Int)=>Int)={
  	f(a,b)
  };System.out.println("""f3: (a: Int, b: Int, f: (Int, Int) => Int)Int""");$skip(34); val res$2 = 
  
  f3(2,3,(a:Int,b:Int)=>{a*b});System.out.println("""res2: Int = """ + $show(res$2));$skip(21); val res$3 = 
  f3(2,3,(a,b)=>a*b);System.out.println("""res3: Int = """ + $show(res$3));$skip(14); val res$4 = 
  f3(2,3,_*_);System.out.println("""res4: Int = """ + $show(res$4));$skip(90); 
  
  //练习1：根据调用，定义一个高阶函数
  def f4(a:String,b:Int,f:(String,Int)=>String)={
  	f(a,b);
  };System.out.println("""f4: (a: String, b: Int, f: (String, Int) => String)String""");$skip(47); val res$5 = 
  
  f4("hello",3,(a:String,b:Int)=>{a.*(b)} );System.out.println("""res5: String = """ + $show(res$5));$skip(31); val res$6 = 
  f4("hello",3,(a,b)=>a.*(b) );System.out.println("""res6: String = """ + $show(res$6));$skip(21); val res$7 = 
  f4("hello",3,_*_ );System.out.println("""res7: String = """ + $show(res$7));$skip(67); 
  
  //练习2：
  def f5(a:Int,b:Int,f:(Int,Int)=>Int)={
  	f(a,b)
  };System.out.println("""f5: (a: Int, b: Int, f: (Int, Int) => Int)Int""");$skip(47); val res$8 = 
  f5(10,20,(a:Int,b:Int)=>{if(a>b) a else b} );System.out.println("""res8: Int = """ + $show(res$8));$skip(37); val res$9 = 
  f5(10,20,(a,b)=>if(a>b) a else b );System.out.println("""res9: Int = """ + $show(res$9));$skip(64); 
  
  //练习3：
  def f6(s:String,f:(String)=>String)={
  	f(s)
  };System.out.println("""f6: (s: String, f: String => String)String""");$skip(49); val res$10 = 
  f6("file01.txt",(s:String)=>{s.takeRight(4)} );System.out.println("""res10: String = """ + $show(res$10));$skip(40); val res$11 = 
  f6("file01.txt",s => s.takeRight(4) );System.out.println("""res11: String = """ + $show(res$11));$skip(35); val res$12 = 
  f6("file01.txt",_.takeRight(4) );System.out.println("""res12: String = """ + $show(res$12));$skip(71); 
  
  //练习4：
  def f7(s:String,f:(String)=>Array[String])={
  	f(s)
  };System.out.println("""f7: (s: String, f: String => Array[String])Array[String]""");$skip(48); val res$13 = 
  f7("hello world",(s:String)=>{s.split(" ")} );System.out.println("""res13: Array[String] = """ + $show(res$13));$skip(39); val res$14 = 
  f7("hello world",s => s.split(" ") );System.out.println("""res14: Array[String] = """ + $show(res$14));$skip(34); val res$15 = 
  f7("hello world",_.split(" ") );System.out.println("""res15: Array[String] = """ + $show(res$15));$skip(31); 
  
  val a1=Array(1,2,3,4,5,6);System.out.println("""a1  : Array[Int] = """ + $show(a1 ));$skip(35); 
  
  val a3=a1.filter { a => a>3 };System.out.println("""a3  : Array[Int] = """ + $show(a3 ))}
  
  
  
}
