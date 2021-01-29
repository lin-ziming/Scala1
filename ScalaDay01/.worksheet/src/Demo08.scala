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
object Demo08 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(422); 
  println("Welcome to the Scala worksheet");$skip(46); 
  
  //1.
  for(i <- 10.to(0,-1)) println(i);$skip(99); 
	//2.编写一个函数countdown(n:Int)，打印从n到0的数字
	def countdown(n:Int)={
		for(i <- n.to(0,-1)) println(i)
	};System.out.println("""countdown: (n: Int)Unit""");$skip(15); 
  countdown(5);$skip(121); 
	//3.编写一个循环，将整数数组中相邻的元素置换
	//val a1=Array(1,2,3,4,5,6)
	//通过for处理a1,处理后:a1中Array(2,1,4,3,6,5)
	val a1=Array(1,2,3,4,5,6);System.out.println("""a1  : Array[Int] = """ + $show(a1 ));$skip(97); 
	//val a1=Array(1)
	for(i <- 1.to(a1.length-1,2) ){
		var t=a1(i)
		a1(i)=a1(i-1)
		a1(i-1)=t
	};$skip(28); 
  println(a1.mkString(" "));$skip(22); 
  a1.foreach(println);$skip(316); 

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
	};System.out.println("""mi: (x: Int, n: Int)Double""");$skip(9); val res$0 = 
	mi(2,0);System.out.println("""res0: Double = """ + $show(res$0));$skip(10); val res$1 = 
	mi(2,-1);System.out.println("""res1: Double = """ + $show(res$1));$skip(291); 
	
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
	};System.out.println("""miRecursion: (x: Int, n: Int)Double""");$skip(19); val res$2 = 
	miRecursion(2,10);System.out.println("""res2: Double = """ + $show(res$2));$skip(19); val res$3 = 
	miRecursion(2,-1);System.out.println("""res3: Double = """ + $show(res$3))}
	
	
	
}
