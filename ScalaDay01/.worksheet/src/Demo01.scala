/**
 
*/
object Demo01 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
  println("Welcome to the Scala worksheet");$skip(25); 
  println("hello scala");$skip(28); 
  
  // 声明一个变量
  var v1=100;System.out.println("""v1  : Int = """ + $show(v1 ));$skip(12); 
  
  v1=200;$skip(28); 
  
  // 声明一个常量
  val v2=100;System.out.println("""v2  : Int = """ + $show(v2 ))}
  
}
