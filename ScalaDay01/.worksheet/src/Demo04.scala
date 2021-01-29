/**
*/
object Demo04 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 
  println("Welcome to the Scala worksheet");$skip(27); 
  
  val a1=Array(1,2,3,4);System.out.println("""a1  : Array[Int] = """ + $show(a1 ));$skip(17); 
  
  var index=0;System.out.println("""index  : Int = """ + $show(index ));$skip(67); 
  
  while(index<a1.length){
  	println(a1(index))
  	index+=1
  };$skip(29); 
  
  for(i <- a1)println(i);$skip(66); 
  
  //练习1：for打印出1~10的所有数字
  
  for(i <- 1.to(10)){
  	println(i)
  };$skip(67); 
  
  //练习2：for打印出1~10的所有奇数数字
  for(i <- 1.to(10,2)) println(i);$skip(72); 
  //练习3：for打印出1~10的所有偶数数字
  for(i <- 1.to(10))
  	if(i%2==0) println(i);$skip(85); 
	//练习4：for打印
	// *
  // **
  // ***
  // ****
	for(i <- 1.to(4))
		println("*".*(i));$skip(128); 

	//练习5：for打印99乘法表，每行中，项与项之间使用制表符分隔 \t
	for(i <- 1 to 9){
		for(j <- 1 to i){
			print(j+"*"+i+"="+i*j+"\t")
		}
		println()
	};$skip(91); 
  //练习6：for打印出1~10中大于5的偶数数据
  for(i <- 1 to 10){
  	if(i>5 && i%2==0){
  		println(i)
  	}
  };$skip(56); 
  
  for(i <- 1 to 10; if i>5 && i%2==0) println(i);$skip(49); 
  for(i <- 1 to 10; if i>5;if i%2==0) println(i);$skip(98); 
  for(a <- 1 to 9; b <- 1 to a; val sep=if(a==b) "\r\n" else "\t"){
  	print(s"$b*$a=${b*a}$sep")
  };$skip(67); 
  
  //遍历a2，将a2中大于3的数据拿出来，返回到新的数组中
  val a2=Array(1,2,3,4,5,6);System.out.println("""a2  : Array[Int] = """ + $show(a2 ));$skip(40); 
  
  val a3=for(i <- a2;if i>3)yield{i};System.out.println("""a3  : Array[Int] = """ + $show(a3 ));$skip(26); 
  
  val l1=List(1,2,3,4);System.out.println("""l1  : List[Int] = """ + $show(l1 ));$skip(34); 
  
  val l2=for(i <- l1)yield {i};System.out.println("""l2  : List[Int] = """ + $show(l2 ))}
  
  
}
