object Demo07 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(80); 
  
  val l1=List(("tom","M",23),("rose","F",18),("jim","M",30),("jary","M",25));System.out.println("""l1  : List[(String, String, Int)] = """ + $show(l1 ));$skip(58); 
  
  //练习1：计算出l1中所有人的年龄和
  val r1=l1.map{ x => x._3 }.sum;System.out.println("""r1  : Int = """ + $show(r1 ));$skip(99); 
  
  //练习2:对l1操作，返回的新集合List("tom-M-23","rose-F-18")
  val r2=l1.map{ x => x._1+"-"+x._2+"-"+x._3 };System.out.println("""r2  : List[String] = """ + $show(r2 ));$skip(71); 
  
  val r3=l1.map{ case(name,gender,age) => name+"-"+gender+"-"+age };System.out.println("""r3  : List[String] = """ + $show(r3 ));$skip(131); 
  //练习3：计算l1中男性年龄最大的前两个人的年龄均值
  val r4=l1.filter{ x => x._2=="M" }
  				.map{ x => x._3 }
  				.sortBy{ x => -x }
  				.take(2);System.out.println("""r4  : List[Int] = """ + $show(r4 ));$skip(35); 
  val r5=r4.sum.toDouble/r4.length;System.out.println("""r5  : Double = """ + $show(r5 ));$skip(61); 
  
  
  val l2=List("hello","world","hello","hello","world");System.out.println("""l2  : List[String] = """ + $show(l2 ));$skip(85); 
  
  //groupBy 分组：按照指定的匿名函数条件进行分组
  //下面表示按单词分组
  val r6=l2.groupBy { word => word };System.out.println("""r6  : scala.collection.immutable.Map[String,List[String]] = """ + $show(r6 ));$skip(62); 
  val l3=List("hello world","hello hello world","hello 2008");System.out.println("""l3  : List[String] = """ + $show(l3 ));$skip(159); 
  val r7=l3.flatMap{ x => x.split(" ") }
  				.groupBy{ word => word}
  				.mapValues{ list => list.length }
  				.toList
  				.foreach{ x => println(x) };System.out.println("""r7  : Unit = """ + $show(r7 ));$skip(218); 
  //val r8=r7.map{ case(word,list) => (word,list.size) }
  
  val r9=l3.flatMap { line => line.split(" ") }
  				.map { word => (word,1) }
  				.groupBy{ x => x._1 }
  				.mapValues{ list => list.map{x=>x._2}.sum };System.out.println("""r9  : scala.collection.immutable.Map[String,Int] = """ + $show(r9 ))}
           
  
  
  
}
