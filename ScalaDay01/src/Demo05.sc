import util.control.Breaks._

/**
知识点
1.scala的异常处理机制和结构同java。不同之处在于scala的catch中,通过case来捕获具体异常
2.scala的match case类比于java的switch case。
match case有返回值,可以接
3.scala所有类的顶级父类是Any,类比于Java的Object。当返回值类型不一致时,会返回Any
4.scala同java,通过import关键字导包。但是scala的导包可以出现代码的任意位置,需要注意作用域问题
5.scala没有continue关键字，需要通过break来实现
如果breakable()在循环外，是break效果
如果breakable()在循环内，是continue效果


*/
object Demo05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
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
  }                                               //> other error
                                                  //| end
    
  val s="hello"                                   //> s  : String = hello
  
  val r1=s match{
  	case "hello"=>{
  		println("123")
  		100
  	}
  	
  	case "world"=>{
  	 println("456")
  	  200
  		
  	  
  	}
  
  }                                               //> 123
                                                  //| r1  : Int = 100
 
  
  breakable(
	  for(i<-1 to 10){
	  	if(i>8){
	  		break
	  	}else{
	  		println(i)
	  	}
  
  
  }
  
  )                                               //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
   for(i<-1 to 10){
   		breakable(
	   		if(i==8){
	   		  //continue效果
	   			break
	   		}else{
	   			println(i)
	   		}
   		)                                 //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 9
                                                  //| 10
   		
   
   }
  
  
}