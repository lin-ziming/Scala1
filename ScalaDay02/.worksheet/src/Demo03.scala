object Demo03 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(61); 
  
  //创建一个定长数组。一经声明，长度不能改变(不能追加新元素)
  val a1=Array(1,2,3,4);System.out.println("""a1  : Array[Int] = """ + $show(a1 ));$skip(46); 
  //创建一个定长数组并指定了长度
  val a2=new Array[Int](3);System.out.println("""a2  : Array[Int] = """ + $show(a2 ));$skip(80); 
  
  //创建一个变长数组并赋值，可以追加新元素
  val a3=scala.collection.mutable.ArrayBuffer(1,2,3);System.out.println("""a3  : scala.collection.mutable.ArrayBuffer[Int] = """ + $show(a3 ));$skip(50); val res$0 = 
  
  //通过下标操作Array，下标从0开始。apply可以省略
  a1.apply(0);System.out.println("""res0: Int = """ + $show(res$0));$skip(8); val res$1 = 
  a1(0);System.out.println("""res1: Int = """ + $show(res$1));$skip(11); 
  a1(0)=10;$skip(5); val res$2 = 
  a1;System.out.println("""res2: Array[Int] = """ + $show(res$2));$skip(175); 
  
  //val a4=Array(1,2,3,4,5)
  //for(i <- 0.until(a4.length,2)){
  	//var t=a1(i)
		//a1(i)=a1(i+1)
		//a1(i+1)=t
  //}
  
  
  
  //练习1：求出a5的均值
  val a5=Array(2,1,7,4,3,6);System.out.println("""a5  : Array[Int] = """ + $show(a5 ));$skip(38); 
  
  val r4=a5.sum.toDouble/a5.length;System.out.println("""r4  : Double = """ + $show(r4 ));$skip(20); 
  val r5=a5.take(3);System.out.println("""r5  : Array[Int] = """ + $show(r5 ));$skip(43); 
  
  //练习
  var r6=a5.drop(1).dropRight(1);System.out.println("""r6  : Array[Int] = """ + $show(r6 ));$skip(16); val res$3 = 
  r6.max-r6.min;System.out.println("""res3: Int = """ + $show(res$3));$skip(28); 
  
  
  val a6=Array(1,2,3);System.out.println("""a6  : Array[Int] = """ + $show(a6 ));$skip(22); 
  val a7=Array(3,4,5);System.out.println("""a7  : Array[Int] = """ + $show(a7 ));$skip(30); 
 	
 	val r11=a6.intersect(a7);System.out.println("""r11  : Array[Int] = """ + $show(r11 ));$skip(23); 
  val r12=a6.union(a7);System.out.println("""r12  : Array[Int] = """ + $show(r12 ));$skip(22); 
  val r13=a6.diff(a7);System.out.println("""r13  : Array[Int] = """ + $show(r13 ));$skip(22); 
  val r14=a7.diff(a6);System.out.println("""r14  : Array[Int] = """ + $show(r14 ));$skip(26); 
  
  val r15=r12.distinct;System.out.println("""r15  : Array[Int] = """ + $show(r15 ));$skip(30); 
  
  
  val a8=Array(2,1,3,4);System.out.println("""a8  : Array[Int] = """ + $show(a8 ));$skip(24); 
  
  val r16=a8.reverse;System.out.println("""r16  : Array[Int] = """ + $show(r16 ));$skip(25); 
  
  val r17=a8.mkString;System.out.println("""r17  : String = """ + $show(r17 ));$skip(27); 
  val r18=a8.mkString(",");System.out.println("""r18  : String = """ + $show(r18 ));$skip(46); 
  
  
  //filter:
  val a9=Array(1,2,3,4,5,6);System.out.println("""a9  : Array[Int] = """ + $show(a9 ));$skip(61); 
  
  //练习3
  val r19=a9.filter{num:Int=>{num>3 && num%2==0}};System.out.println("""r19  : Array[Int] = """ + $show(r19 ));$skip(47); 
  val r20=a9.filter{num => num>3 && num%2==0 };System.out.println("""r20  : Array[Int] = """ + $show(r20 ));$skip(82); 
  
  
  //中午练习4：基于a10，过滤出男性数据
  val a10=Array("tom M 23","rose F 18","jary M 25");System.out.println("""a10  : Array[String] = """ + $show(a10 ));$skip(26); val res$4 = 
  math.pow(2.toDouble,10);System.out.println("""res4: Double = """ + $show(res$4));$skip(54); 
  val r21=a10.filter{line => line.split(" ")(1)=="M"};System.out.println("""r21  : Array[String] = """ + $show(r21 ));$skip(58); 
  val r22=a10.filter{line => line.split(" ")(2).toInt>18};System.out.println("""r22  : Array[String] = """ + $show(r22 ))}
  
}
