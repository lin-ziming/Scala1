object Demo09 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(19); 
  
  val num="100";System.out.println("""num  : String = """ + $show(num ));$skip(41); 
  
  implicit def f1(s:String)={s.toInt};System.out.println("""f1: (s: String)Int""");$skip(35); 
  
  //可以使用隐式转换机制
  val v1:Int=num;System.out.println("""v1  : Int = """ + $show(v1 ));$skip(24); 
  
  val s="file01.txt"
  
  implicit class AuthStr(str:String){
  	def suffix()={
  		s.takeRight(4)
  	}
  };System.out.println("""s  : String = """ + $show(s ));$skip(100); val res$0 = 
  s.suffix();System.out.println("""res0: String = """ + $show(res$0))}
  
}
