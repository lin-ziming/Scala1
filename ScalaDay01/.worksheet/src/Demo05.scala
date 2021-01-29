import util.control.Breaks._
/**
知识点
1.scala的异常处理机制和结构同java。不同之处在于scala的catch中，通过case来捕获具体异常
*/
object Demo05 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(155); 
  println("Welcome to the Scala worksheet");$skip(210); 
  
  try{
  	throw new RuntimeException
  }catch{
  
  	case t:NullPointerException=>{
  		println("null error")
  	}
  	case t:Exception=>{
  		println("other error")
  	}
  
  }finally{
  	println("end")
  };$skip(19); 
  
  val s="hello";System.out.println("""s  : String = """ + $show(s ));$skip(126); 
  
  var r1=s match{
  	case "hello" =>{
  		println("123")
  		100
  	}
  	case "world" =>{
  		println("456")
  		
  	}
  };System.out.println("""r1  : AnyVal = """ + $show(r1 ));$skip(106); 
  
  
  breakable(
	  for(i <- 1 to 10){
	  	if(i>8){
	  		break
	  	}else{
	  		println(i)
	  	}
		}
  );$skip(116); 
  for(i <- 1 to 10){
  	breakable(
	  	if(i==8){
	  		//continue效果
	  		break
	  	}else{
	  		println(i)
	  	}
	  )}
  }
  
  
  
}
