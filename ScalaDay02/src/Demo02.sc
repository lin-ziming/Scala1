/**
知识点
1.scala支持柯里化技术。允许将接受多个参数的函数转变为接收单一参数的函数
2.scala柯里化技术可以实现自建控制结构，在调用函数时，层次结构更加清晰，
比如:def f32(a:Int,b:Int)(f:(Int,Int)=>Int)={f(a,b)}
def f32(a:Int,b:Int)(f1:(Int,Int)=>Int,f2:(Int,Int)=>Int)={}
3.scala支持懒值机制。声明时不赋值，而是调用时才赋值,比如:
lazy val v2=100
注意:lazy只能修改常量 val

*/
object Demo02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def f1(a:Int,b:Int)={a+b}                       //> f1: (a: Int, b: Int)Int
  
  def f11(a:Int)(b:Int)={a+b}                     //> f11: (a: Int)(b: Int)Int
  
  f1(2,3)                                         //> res0: Int = 5
  f11(2)(3)                                       //> res1: Int = 5
  
  //练习1:写出f2等价的柯里化形式
  def f2(a:Int,b:Int,c:Int)={a+b+c}               //> f2: (a: Int, b: Int, c: Int)Int
  
  def f21(a:Int)(b:Int)(c:Int)={a+b+c}            //> f21: (a: Int)(b: Int)(c: Int)Int
  
  def f22(a:Int,b:Int)(c:Int)={a+b+c}             //> f22: (a: Int, b: Int)(c: Int)Int
  
  def f23(a:Int)(b:Int,c:Int)={a+b+c}             //> f23: (a: Int)(b: Int, c: Int)Int
  
  
  //练习2:写出f3等价的柯里化形式
  def f3(a:Int,b:Int,f:(Int,Int)=>Int)={f(a,b)}   //> f3: (a: Int, b: Int, f: (Int, Int) => Int)Int
  
  def f31(a:Int)(b:Int)(f:(Int,Int)=>Int)={f(a,b)}//> f31: (a: Int)(b: Int)(f: (Int, Int) => Int)Int
  
  //留意这种形式，通过柯里化将参数分为两部分，一部分是普通参数，另一部分是匿名函数
  //这种形式称为自建控制结构，在调用函数时，层次结构更加清晰
  def f32(a:Int,b:Int)(f:(Int,Int)=>Int)={f(a,b)} //> f32: (a: Int, b: Int)(f: (Int, Int) => Int)Int
  
  def f33(a:Int)(b:Int,f:(Int,Int)=>Int)={f(a,b)} //> f33: (a: Int)(b: Int, f: (Int, Int) => Int)Int
  
  
  f32(2,3)(_+_)                                   //> res2: Int = 5
  
  
  
  val v1=100                                      //> v1  : Int = 100
  //lazy懒值
  lazy val v2=100                                 //> v2: => Int
  
  println(v2)                                     //> 100
  
 
  
  
}