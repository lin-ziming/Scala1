/**
课后作业:
1.针对下列Java循环编写一个Scala版本:

for(int i=10;i>=0;i–)

     System.out.println(i);
     
2.编写一个函数countdown(n:Int)，打印从n到0的数字

3.编写一个循环，将整数数组中相邻的元素置换

val a1=Array(1,2,3,4,5,6)

通过for处理a1,处理后:a1中Array(2,1,4,3,6,5)

3+.编写函数计算x的n次方，其中n是整数，要考虑n是0，正偶数，正奇数，负数这几种情况。
比如当x=2时，此函数要算出2^4,2^3,2^0,2^(-1)对应的值
mi(x:Int,n:Int):Double={}
mi(2,10)=1024
mi(2,-1)=0.5
*/
object Demo08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //1.
  for(i <- 10.to(0,-1)) println(i)                //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
	//2.编写一个函数countdown(n:Int)，打印从n到0的数字
	def countdown(n:Int)={
		for(i <- n.to(0,-1)) println(i)
	}                                         //> countdown: (n: Int)Unit
  countdown(5)                                    //> 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
	//3.编写一个循环，将整数数组中相邻的元素置换
	//val a1=Array(1,2,3,4,5,6)
	//通过for处理a1,处理后:a1中Array(2,1,4,3,6,5)
	val a1=Array(1,2,3,4,5,6)                 //> a1  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
	//val a1=Array(1)
	for(i <- 1.to(a1.length-1,2) ){
		var t=a1(i)
		a1(i)=a1(i-1)
		a1(i-1)=t
	}
  println(a1.mkString(" "))                       //> 2 1 4 3 6 5
  a1.foreach(println)                             //> 2
                                                  //| 1
                                                  //| 4
                                                  //| 3
                                                  //| 6
                                                  //| 5

	//3+.编写函数计算x的n次方，其中n是整数，要考虑n是0，正偶数，正奇数，负数这几种情况。
	//比如当x=2时，此函数要算出2^4,2^3,2^0,2^(-1)对应的值
	//mi(x:Int,n:Int):Double={}
	//mi(2,10)=1024
	//mi(2,-1)=0.5
	//非递归算法
	def mi(x:Int,n:Int):Double={
		var res = 1.0
		if (n>=0){
			for(i <- n.to(1,-1)) res*=x
		}else{
			for(i <- 1.to(-n)) res*=x
			res=1/res
		}
		res
	}                                         //> mi: (x: Int, n: Int)Double
	mi(2,0)                                   //> res0: Double = 1.0
	mi(2,-1)                                  //> res1: Double = 0.5
	
	//递归算法
	def miRecursion(x:Int,n:Int):Double={
		var res = 1.0
		var flag = true
		if (n<0){
				flag = false
				miPositive(x,-n)
		}else
			miPositive(x,n)
		def miPositive(x:Int,n:Int):Double={
			if(n>0){
				res*=x
				miPositive(x,n-1)
			}
			res
		}
		if (flag) res else 1/res
	}                                         //> miRecursion: (x: Int, n: Int)Double
	miRecursion(2,10)                         //> res2: Double = 1024.0
	miRecursion(2,-1)                         //> res3: Double = 0.5
	
	
	
}