/**

*/
object Demo03 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 
  println("Welcome to the Scala worksheet");$skip(15); 
  
  val num=5;System.out.println("""num  : Int = """ + $show(num ));$skip(110); 
  
  val r1=if(num>5){
  	println("big")
  	100
  	"hello"
  }else{
  	println("small")
  	200
  	"world"
  };System.out.println("""r1  : String = """ + $show(r1 ));$skip(75); 
  
  //练习1：要求根据地区判断薪资。如果地区是bj，则薪资=10000。如果地区是其他地区,则薪资=9000
  val addr="bj";System.out.println("""addr  : String = """ + $show(addr ));$skip(45); 
  val salary = if(addr=="bj")10000 else 9000;System.out.println("""salary  : Int = """ + $show(salary ))}
  
}
