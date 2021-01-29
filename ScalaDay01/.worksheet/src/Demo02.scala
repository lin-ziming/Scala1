/**
学习String相关的方法
*/
object Demo02 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(80); 
  println("Welcome to the Scala worksheet");$skip(25); 
  
  val s="hello world";System.out.println("""s  : String = """ + $show(s ));$skip(25); 
  
  val r1=s.split(" ");System.out.println("""r1  : Array[String] = """ + $show(r1 ));$skip(23); 
  
  val r2=s.length();System.out.println("""r2  : Int = """ + $show(r2 ));$skip(28); 
  
  val r3=s.contains("h");System.out.println("""r3  : Boolean = """ + $show(r3 ));$skip(19); 
  
  val r4=s.*(2);System.out.println("""r4  : String = """ + $show(r4 ));$skip(22); 
  
  val r5=s.take(2);System.out.println("""r5  : String = """ + $show(r5 ));$skip(27); 
  
  val r6=s.takeRight(2);System.out.println("""r6  : String = """ + $show(r6 ));$skip(22); 
  
  val r7=s.drop(2);System.out.println("""r7  : String = """ + $show(r7 ));$skip(27); 
  
  val r8=s.dropRight(2);System.out.println("""r8  : String = """ + $show(r8 ));$skip(52); 
  
  
  //练习1：获取手机号的后4位
  val s2="tom1234124231MSH";System.out.println("""s2  : String = """ + $show(s2 ));$skip(38); 
  val r9=s2.takeRight(7).dropRight(3);System.out.println("""r9  : String = """ + $show(r9 ));$skip(37); 
  
  val r10=s2.takeRight(3).take(1);System.out.println("""r10  : String = """ + $show(r10 ));$skip(18); 
  
  
  val num=1;System.out.println("""num  : Int = """ + $show(num ));$skip(18); 
  val r11=num+5*3;System.out.println("""r11  : Int = """ + $show(r11 ));$skip(24); 
  val r12=num.+(5).*(3);System.out.println("""r12  : Int = """ + $show(r12 ));$skip(21); 
  
  val r13=1.to(5);System.out.println("""r13  : scala.collection.immutable.Range.Inclusive = """ + $show(r13 ));$skip(20); 
	val r14=1.until(5);System.out.println("""r14  : scala.collection.immutable.Range = """ + $show(r14 ));$skip(20); 

	val r15=1.to(5,2);System.out.println("""r15  : scala.collection.immutable.Range.Inclusive = """ + $show(r15 ));$skip(52); 
	
	//练习3：生成这样一个区间集合 [5,4,3,2,1]
	val r16=5.to(1,-1);System.out.println("""r16  : scala.collection.immutable.Range.Inclusive = """ + $show(r16 ));$skip(25); 
	val r17=1.to(5).reverse;System.out.println("""r17  : scala.collection.immutable.Range = """ + $show(r17 ))}
	

}
