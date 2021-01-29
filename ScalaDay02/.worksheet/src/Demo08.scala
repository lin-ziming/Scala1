object Demo08 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(32); 
  
  val p1=new People("bob",0);System.out.println("""p1  : People = """ + $show(p1 ));$skip(30); 
  val p2=new People("bob",24);System.out.println("""p2  : People = """ + $show(p2 ));$skip(29); 
  val p3=new People("alice");System.out.println("""p3  : People = """ + $show(p3 ));$skip(24); 
  val p4=new People(18);System.out.println("""p4  : People = """ + $show(p4 ));$skip(22); 
  val p5=new People();System.out.println("""p5  : People = """ + $show(p5 ));$skip(23); 
  
  p1.setName("tom");$skip(16); 
  p1.setAge(23);$skip(18); val res$0 = 
  
  p1.getName();System.out.println("""res0: String = """ + $show(res$0));$skip(14); val res$1 = 
  p1.getAge();System.out.println("""res1: Int = """ + $show(res$1));$skip(14); 
  
  p1.say();$skip(22); val res$2 = 
  
   
  p2.getName();System.out.println("""res2: String = """ + $show(res$2));$skip(14); val res$3 = 
  p2.getAge();System.out.println("""res3: Int = """ + $show(res$3));$skip(18); val res$4 = 
  
 	p3.getName();System.out.println("""res4: String = """ + $show(res$4));$skip(14); val res$5 = 
 	p3.getAge();System.out.println("""res5: Int = """ + $show(res$5));$skip(18); val res$6 = 
  
  p4.getName();System.out.println("""res6: String = """ + $show(res$6));$skip(14); val res$7 = 
 	p4.getAge();System.out.println("""res7: Int = """ + $show(res$7));$skip(18); val res$8 = 
  
  p5.getName();System.out.println("""res8: String = """ + $show(res$8));$skip(14); val res$9 = 
 	p5.getAge();System.out.println("""res9: Int = """ + $show(res$9));$skip(18); 
 	
 	People.run();$skip(32); 
 	
 	val i1=Item("huawei",3999);System.out.println("""i1  : Item = """ + $show(i1 ));$skip(14); val res$10 = 
 	
 	i1.title;System.out.println("""res10: String = """ + $show(res$10));$skip(11); val res$11 = 
 	i1.price;System.out.println("""res11: Double = """ + $show(res$11));$skip(17); 
 	
 	val i2=Item;System.out.println("""i2  : Item.type = """ + $show(i2 ));$skip(17); 
 	
 	println(i1);$skip(14); 
 	println(i2);$skip(155); 
 	
 	
 	//Option类有两个子类：Some和None
 	//可以通过getOrElse来进行取值操作，如果是None,则返回指定的默认值
 	def f1(a:Int,b:Int)={
 		if(b!=0){
 			Some(a/b)
 		}else{
 			None
 		}
 	};System.out.println("""f1: (a: Int, b: Int)Option[Int]""");$skip(13); val res$12 = 
 	
 	f1(4,2);System.out.println("""res12: Option[Int] = """ + $show(res$12));$skip(10); val res$13 = 
 	f1(4,0);System.out.println("""res13: Option[Int] = """ + $show(res$13));$skip(23); val res$14 = 
 	f1(4,2).getOrElse(0);System.out.println("""res14: Int = """ + $show(res$14));$skip(26); val res$15 = 
 	f1(4,0).getOrElse(1000);System.out.println("""res15: Int = """ + $show(res$15))}
  
}
