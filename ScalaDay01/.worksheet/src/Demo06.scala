/**

def 函数名(形参名:参数类型,...):返回值类型={方法体}
*/
object Demo06 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(101); 
  println("Welcome to the Scala worksheet");$skip(40); 
  
  
  
  def f1(a:Int, b:Int):Int=a+b;System.out.println("""f1: (a: Int, b: Int)Int""");$skip(13); val res$0 = 
  
  f1(2,3);System.out.println("""res0: Int = """ + $show(res$0));$skip(29); 
  
  def f2(a:Int,b:Int)=a+b;System.out.println("""f2: (a: Int, b: Int)Int""");$skip(72); 
  
  
  
  
  //scala函数支持默认参数机制
  def f5(a:Int,b:Int=100)={
  	a+b
  };System.out.println("""f5: (a: Int, b: Int)Int""");$skip(12); val res$1 = 
  
  f5(50);System.out.println("""res1: Int = """ + $show(res$1));$skip(76); 
  
  
  //练习1：编写一个函数，可以提取身份证号后4位
  def f6(a:String)={
  	a.takeRight(4)
  };System.out.println("""f6: (a: String)String""");$skip(18); val res$2 = 
  f6("123456789");System.out.println("""res2: String = """ + $show(res$2));$skip(124); 
  
  //练习2:编写一个函数，传入两个整型数字，start end.打印出这段范围内的数字
  def f7(start:Int,end:Int)={
  	for(i <- start to end)
  		println(i)
  };System.out.println("""f7: (start: Int, end: Int)Unit""");$skip(10); 
  f7(1,5);$skip(116); 
  
  //练习3：编写一个函数，传入一个整型数组，打印出数组的所有偶数数据
  def f8(a:Array[Int])={
  	for(i <- a; if i%2==0){
  		println(i)
  	}
  };System.out.println("""f8: (a: Array[Int])Unit""");$skip(31); 
  f8(Array(1,2,3,4,5,6,7,8,9));$skip(51); 
  
  //scala函数支持变长参数机制
  def f9(a:Int*)={
  	a
  };System.out.println("""f9: (a: Int*)Seq[Int]""");$skip(12); val res$3 = 
  f9(1,2,3);System.out.println("""res3: Seq[Int] = """ + $show(res$3))}
}
