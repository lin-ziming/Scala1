object Demo02 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(31); 
  
  def f1(a:Int,b:Int)={a+b};System.out.println("""f1: (a: Int, b: Int)Int""");$skip(33); 
  
  def f11(a:Int)(b:Int)={a+b};System.out.println("""f11: (a: Int)(b: Int)Int""");$skip(13); val res$0 = 
  
  f1(2,3);System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 
  f11(2)(3);System.out.println("""res1: Int = """ + $show(res$1));$skip(60); 
  
  //练习1：写出f2等价的柯里化形式
  def f2(a:Int,b:Int,c:Int)={a+b+c};System.out.println("""f2: (a: Int, b: Int, c: Int)Int""");$skip(39); 
  def f21(a:Int)(b:Int)(c:Int)={a+b+c};System.out.println("""f21: (a: Int)(b: Int)(c: Int)Int""");$skip(38); 
  def f22(a:Int)(b:Int,c:Int)={a+b+c};System.out.println("""f22: (a: Int)(b: Int, c: Int)Int""");$skip(38); 
  def f23(a:Int,b:Int)(c:Int)={a+b+c};System.out.println("""f23: (a: Int, b: Int)(c: Int)Int""");$skip(70); 
  
  //练习2：写出f3等价的柯里化
  def f3(a:Int,b:Int,f:(Int,Int)=>Int)={f(a,b)};System.out.println("""f3: (a: Int, b: Int, f: (Int, Int) => Int)Int""");$skip(104); 
  //留意这种形式，通过柯里化将参数分为两部分，一部分是普通参数，领一部是匿名函数
  //这种形式称为
  def f3(a:Int)(b:Int)(f:(Int,Int)=>Int)={f(a,b)};System.out.println("""f3: (a: Int)(b: Int)(f: (Int, Int) => Int)Int""");$skip(49); 
  def f3(a:Int,b:Int)(f:(Int,Int)=>Int)={f(a,b)};System.out.println("""f3: (a: Int, b: Int)(f: (Int, Int) => Int)Int""");$skip(49); 
  def f3(a:Int)(b:Int,f:(Int,Int)=>Int)={f(a,b)};System.out.println("""f3: (a: Int)(b: Int, f: (Int, Int) => Int)Int""")}
  
  
  
}
