/**
知识点
1.可以通过implicit关键字来定义隐式转换方法。作用是将指定类型转换成某一个目标类型。
比如:
implicit def f1(s:String)={s.toInt}
表示将String转换成Int

2.需要注意:隐式转换方法的参数列表,只能有一个参数
3.需要注意:在同一个作用域中,相同的类型转换只能存在一个
4.可以通过implicti关键字来定义隐式类,为指定类型添加自定义的方法
需要注意:隐式类的主构造器只能有一个参数,即目标类型

*/
object Demo09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val num="100"                                   //> num  : String = 100
  
  implicit def f1(s:String)={s.toInt}             //> f1: (s: String)Int
  

  
  //如果不使用显式类型转换,还要使其合法。可以使用隐式转换机制
  val v1:Int=num                                  //> v1  : Int = 100
  
  
  val s="file01.txt"                              //> s  : String = file01.txt
  
  implicit class AuthStr(str:String){
  	
  	def suffix()={
  		s.takeRight(4)
  	}
  }
  
  //想调用此方法获取文件名的后缀   .txt
  s.suffix()                                      //> res0: String = .txt
}