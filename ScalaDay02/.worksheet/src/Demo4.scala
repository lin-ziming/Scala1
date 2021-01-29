object Demo4 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet");$skip(26); 
  
  val l1=List(1,2,3,4);System.out.println("""l1  : List[Int] = """ + $show(l1 ));$skip(57); 
  
  val l2=scala.collection.mutable.ListBuffer(1,2,3,4);System.out.println("""l2  : scala.collection.mutable.ListBuffer[Int] = """ + $show(l2 ));$skip(17); val res$0 = 
  
  l1.apply(0);System.out.println("""res0: Int = """ + $show(res$0));$skip(8); val res$1 = 
  l1(0);System.out.println("""res1: Int = """ + $show(res$1));$skip(23); 
  
  val a1=l1.toArray;System.out.println("""a1  : Array[Int] = """ + $show(a1 ));$skip(19); 
  val l3=a1.toList;System.out.println("""l3  : List[Int] = """ + $show(l3 ));$skip(23); 
  
  val r1=l3.take(1);System.out.println("""r1  : List[Int] = """ + $show(r1 ));$skip(17); 
  val r2=l3.head;System.out.println("""r2  : Int = """ + $show(r2 ));$skip(18); val res$2 = 
  l3.takeRight(1);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(17); 
  val r3=l3.last;System.out.println("""r3  : Int = """ + $show(r3 ));$skip(26); 
  
  val l4=List(1,2,3,4);System.out.println("""l4  : List[Int] = """ + $show(l4 ));$skip(95); 
  
  //map方法：映射方法。根据匿名函数将元素从原来的形式映射为新的形式
  //并将映射后的数据返回到新的集合中
  
  val r4=l4.map{num => num*2};System.out.println("""r4  : List[Int] = """ + $show(r4 ));$skip(41); 
  
  val r5=l4.map{num => num.toString };System.out.println("""r5  : List[String] = """ + $show(r5 ));$skip(116); 
  
  //练习1：对l5映射，返回的新集合List("tom","rose","jary","jim")
  val l5=List("tom M 23","rose F 18","jary M 25","jim M 30");System.out.println("""l5  : List[String] = """ + $show(l5 ));$skip(45); 
  val r6=l5.map{ line => line.split(" ")(0)};System.out.println("""r6  : List[String] = """ + $show(r6 ));$skip(82); 
  
  //练习2：计算出l5中所有人的年龄之和
  val r7=l5.map{ line => line.split(" ")(2).toInt }.sum;System.out.println("""r7  : Int = """ + $show(r7 ));$skip(118); 
  
  //练习3：计算出l5中男性年龄的均值
  val r8=l5.filter{line=>line.split(" ")(1)=="M"}
  			.map{line=>line.split(" ")(2).toInt };System.out.println("""r8  : List[Int] = """ + $show(r8 ));$skip(35); 
  val r9=r8.sum.toDouble/r8.length;System.out.println("""r9  : Double = """ + $show(r9 ));$skip(62); 
  
  
  val l6=List("hello world","hello scala","hello 2008");System.out.println("""l6  : List[String] = """ + $show(l6 ));$skip(47); 
  
  val r10=l6.map{ line => line.split(" ") };System.out.println("""r10  : List[Array[String]] = """ + $show(r10 ));$skip(57); 
  //扁平化映射
  val r11=l6.flatMap{ line => line.split(" ")};System.out.println("""r11  : List[String] = """ + $show(r11 ));$skip(46); 
  
  
  val l7=List("1 2 5","3 4 8 9","6 7");System.out.println("""l7  : List[String] = """ + $show(l7 ));$skip(95); 
  //练习4：求出l7中数字的极差
  val r12=l7.flatMap{line =>line.split(" ")}
  					.map( num => num.toInt);System.out.println("""r12  : List[Int] = """ + $show(r12 ));$skip(26); 
  val r13=r12.max-r12.min;System.out.println("""r13  : Int = """ + $show(r13 ));$skip(29); 
  
  
  val l8=List(1,2,3,4);System.out.println("""l8  : List[Int] = """ + $show(l8 ));$skip(128); 
  //reduce:归约方法
  //底层迭代处理
  //第1次：a=1 b=2 result=3
  //第2次：a=3 b=3 result=6
  //第3次：a=6 b=4 result=10
  val r14=l8.reduce{_+_};System.out.println("""r14  : Int = """ + $show(r14 ));$skip(49); 
  
  //练习5：求出l8中的阶乘结果
  val r15=l8.reduce{ _*_ };System.out.println("""r15  : Int = """ + $show(r15 ));$skip(28); 
  
  val l9=List(2,1,5,4,3);System.out.println("""l9  : List[Int] = """ + $show(l9 ));$skip(54); 
  //练习6：
  val r16=l9.reduce{(a,b)=>if(a>b)a else b };System.out.println("""r16  : Int = """ + $show(r16 ));$skip(99); 
  
  //sortBy 排序：根据指定的匿名函数条件排序。并将排序后的结果返回到新的集合中
  //下面表示按数字做升序排序
  val r17=l9.sortBy{ num => num };System.out.println("""r17  : List[Int] = """ + $show(r17 ));$skip(42); 
  //降序
  val r18=l9.sortBy{ num => -num };System.out.println("""r18  : List[Int] = """ + $show(r18 ));$skip(46); 
  
  val r19=l9.sortBy{ num => num }.reverse;System.out.println("""r19  : List[Int] = """ + $show(r19 ));$skip(73); 
  
  
  val l10=List("bj 6500","sh 7000","bj 5000","bj 10000","sh 6000");System.out.println("""l10  : List[String] = """ + $show(l10 ));$skip(67); 
  //练习7:
  val r20=l10.sortBy{ line => -line.split(" ")(1).toInt };System.out.println("""r20  : List[String] = """ + $show(r20 ));$skip(73); 
  
  //练习8：基于
  val r21=l10.sortBy{ line => line.split(" ")(0) }.reverse;System.out.println("""r21  : List[String] = """ + $show(r21 ));$skip(147); 
  //练习9：
  val r22=l10.filter{ line => line.split(" ")(0)=="bj" }
  			.map{ line => line.split(" ")(1).toInt }
  			.sortBy { salary => -salary };System.out.println("""r22  : List[Int] = """ + $show(r22 ));$skip(24); 
  val r23=r22(0)-r22(1);System.out.println("""r23  : Int = """ + $show(r23 ))}
  
}
