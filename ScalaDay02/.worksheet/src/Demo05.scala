object Demo05 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(48); 
  
  val m1=Map("tom"->23,"rose"->18,"jim"->25);System.out.println("""m1  : scala.collection.immutable.Map[String,Int] = """ + $show(m1 ));$skip(63); 
  
  val m2=scala.collection.mutable.Map("tom"->23,"rose"->18);System.out.println("""m2  : scala.collection.mutable.Map[String,Int] = """ + $show(m2 ));$skip(18); val res$0 = 
  m1.apply("tom");System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 
  m1("tom");System.out.println("""res1: Int = """ + $show(res$1));$skip(32); val res$2 = 
  
  m2+=("jim"->25,"jary"->30);System.out.println("""res2: Demo05.m2.type = """ + $show(res$2));$skip(27); 
  
  val r1=m1.keys.toList;System.out.println("""r1  : List[String] = """ + $show(r1 ));$skip(27); 
  val r2=m1.values.toArray;System.out.println("""r2  : Array[Int] = """ + $show(r2 ));$skip(45); 
  
  //通过for循环
  for(i <- m1){
  	println(i)
  };$skip(38); 
  for((k,v) <- m1){
  	println(v)
  };$skip(56); 
  
  var m3=Map("book"->10,"gun"->100,"ipad"->1000);System.out.println("""m3  : scala.collection.immutable.Map[String,Int] = """ + $show(m3 ));$skip(43); 
  val m4=for((k,v) <- m3)yield {(k,v*0.9)};System.out.println("""m4  : scala.collection.immutable.Map[String,Double] = """ + $show(m4 ));$skip(61); 
  
  //filter对Map实现过滤
  val m5=m1.filter{ case(k,v)=> v>18 };System.out.println("""m5  : scala.collection.immutable.Map[String,Int] = """ + $show(m5 ));$skip(57); 
  //map对Map实现映射
  val m6=m1.map{ case(k,v) => (k,v+10) };System.out.println("""m6  : scala.collection.immutable.Map[String,Int] = """ + $show(m6 ));$skip(36); 
  val m7=m1.mapValues { v => v+10 };System.out.println("""m7  : scala.collection.immutable.Map[String,Int] = """ + $show(m7 ))}
  
  
  
}
