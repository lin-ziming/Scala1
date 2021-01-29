/**
知识点
1.scala的打印函数:
println 打印并换行
print   打印不换行

2.scala的 if else 作用和结构同java。不同的在于,scala的if else有返回值,可以接
3.scala的Unit 空类型。类比于Java的void
4.scala的通用规则:scala会将方法体{}的最后一行代码作为返回值返回,不需要加return关键字返回
5.scala的打印函数返回值类型为Unit
6.scala的通用化简规则:如果方法体{}只有一行代码,则{}可以省略
7.scala没有三目(三元)运算符,但可以使用if else来代替
*/

object Demo03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val num=3                                       //> num  : Int = 3
  
  val r1=if(num>5)println("big")else println("small")
                                                  //> small
                                                  //| r1  : Unit = ()
  //练习1:要求根据地区判断薪资。如果地区是bj,则薪资=10000。如果其他地区,则薪资=9000
  val addr="bj"                                   //> addr  : String = bj
  val salary=if(addr=="bj")10000 else 9000        //> salary  : Int = 10000
  
  if(num>3){
  	println("big")
  }else if(num==3){
  	println("equal")
  }else{
  	println("small")
  }                                               //> equal
 
 	
 	
}