/**
学习编写scala递归函数
知识点
1.scala递归函数必须显式的声明函数返回值类型。
2.编写递归函数的技巧是掌握两要素:
①找出项与项之间的函数关系
②找出结束条件
3.scala递归函数结束条件的返回值,使用return关键字返回
*/
object Demo01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //给定一个菲波那切数列:2 3 5 8 13 21
  //编写一个递归函数  f1(n:Int):Int,给定第n项，返回对应项的结果。
  //比如f(0)=2   f(4)=13  依次类推
  //函数关系: f(n)=f(n-1)+f(n-2)
  //结束条件: f(0)=2 f(1)=3
  
  def f1(n:Int):Int={
  	if(n==0) return 2
  	if(n==1) return 3
  	else f1(n-1)+f1(n-2)
  	
  
  }                                               //> f1: (n: Int)Int
  
  f1(6)                                           //> res0: Int = 34
  
  //练习1:给定一个数列: 2 3 4 9 16 81 ......
  //编写一个递归函数  f2(n:Int):Int,给定第n项，返回对应项的结果。
  //函数关系:f(n)=f(n-2)*f(n-2)
  //结束条件: f(0)=2 f(1)=3
  
  def f2(n:Int):Int={
  		if(n==0) return 2
  	  if(n==1) return 3
  	  else f2(n-2)*f2(n-2)
  }                                               //> f2: (n: Int)Int
  
  //               0 1 2 3 4 5  6  7
  //练习2:给定一个数列:2 3 4 9 8 27 16 81 ......
  //编写一个递归函数  f3(n:Int):Int,给定第n项，返回对应项的结果。
  //函数关系:n是偶数项,f(n)=2*f(n-2)
  //n是奇数项  f(n)=3*f(n-2)
  //结束条件: f(0)=2 f(1)=3
  
  def f3(n:Int):Int={
  		if(n==0) return 2
  	  if(n==1) return 3
  	  if(n%2==0) 2*f3(n-2)
  	  else 3*f3(n-2)
  
  }                                               //> f3: (n: Int)Int
  
  f3(7)                                           //> res1: Int = 81
  
  def mi(x:Int,n:Int):Double={
     if(n==0) return 1
     if(n%2==0&&n>0) mi(x,n/2)*mi(x,n/2)
     else if(n%2==1&&n>0) x*mi(x,n-1)
     else 1/mi(x,-n)
  	
  }                                               //> mi: (x: Int, n: Int)Double
  
  mi(2,-1)                                        //> res2: Double = 0.5
}