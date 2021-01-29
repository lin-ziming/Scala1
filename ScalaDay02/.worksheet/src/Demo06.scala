object Demo06 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(63); 
  
  //创建一个Tuple 元组。元组用于存数据和取数据
  val t1=(1,2,"hello","world");System.out.println("""t1  : (Int, Int, String, String) = """ + $show(t1 ));$skip(11); val res$0 = 
  
  t1._3;System.out.println("""res0: String = """ + $show(res$0));$skip(26); 
  
  val t2=((1,2),(3,4));System.out.println("""t2  : ((Int, Int), (Int, Int)) = """ + $show(t2 ));$skip(11); val res$1 = 
  t2._2._2;System.out.println("""res1: Int = """ + $show(res$1));$skip(45); 
  
  val t3=(1,2,(3,4,(5,6,(7,Array(8,9)))));System.out.println("""t3  : (Int, Int, (Int, Int, (Int, Int, (Int, Array[Int])))) = """ + $show(t3 ));$skip(20); val res$2 = 
  t3._3._3._3._2(0);System.out.println("""res2: Int = """ + $show(res$2));$skip(48); 
  
  val m1=Map("tom"->23,"rose"->18,"jim"->25);System.out.println("""m1  : scala.collection.immutable.Map[String,Int] = """ + $show(m1 ));$skip(43); 
  
  val m2=m1.filter{ case(k,v) => v>18 };System.out.println("""m2  : scala.collection.immutable.Map[String,Int] = """ + $show(m2 ));$skip(38); 
  
  val m3=m1.filter{ x => x._2>18 };System.out.println("""m3  : scala.collection.immutable.Map[String,Int] = """ + $show(m3 ));$skip(40); 
  val m4=m1.map( x => (x._1, x._2+10) );System.out.println("""m4  : scala.collection.immutable.Map[String,Int] = """ + $show(m4 ))}


  
  
}
