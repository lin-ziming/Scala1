object Demo07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val l1=List(("tom","M",23),("rose","F",18),("jim","M",30),("jary","M",25))
                                                  //> l1  : List[(String, String, Int)] = List((tom,M,23), (rose,F,18), (jim,M,30)
                                                  //| , (jary,M,25))
  
  //练习1:计算出l1中所有人的年龄和
  val r1=l1.map{x=>x._3}.sum                      //> r1  : Int = 96
  
  //练习2:对l1操作,返回的新集合List("tom-M-23","rose-F-18","jim-M-30","jary-M-25")
  
  val r2=l1.map{x=>x._1+"-"+x._2+"-"+x._3}        //> r2  : List[String] = List(tom-M-23, rose-F-18, jim-M-30, jary-M-25)
  
  val r3=l1.map{case(name,gender,age)=>name+"-"+gender+"-"+age}
                                                  //> r3  : List[String] = List(tom-M-23, rose-F-18, jim-M-30, jary-M-25)
  
  //练习3:计算l1中男性年龄最大的前两个人的年龄均值
	
	val r4=l1.filter{x=>x._2=="M"}
	         .map{x=>x._3}
	         .sortBy{x=> -x}
	         .take(2)                         //> r4  : List[Int] = List(30, 25)
	         
	val r5=r4.sum.toDouble/r4.length          //> r5  : Double = 27.5
	
	
	val l2=List("hello","world","hello","hello","world")
                                                  //> l2  : List[String] = List(hello, world, hello, hello, world)
	
	//groupBy 分组:按照指定的匿名函数条件进行分组
	//下面表示按单词分组
	val r6=l2.groupBy { word => word }        //> r6  : scala.collection.immutable.Map[String,List[String]] = Map(world -> Lis
                                                  //| t(world, world), hello -> List(hello, hello, hello))
  //课后作业:统计出l3中的单词频次。比如返回的结果形式:
  //Map(hello->4,world->3)
  val l3=List("hello world","hello hello world","hello world")
                                                  //> l3  : List[String] = List(hello world, hello hello world, hello world)
  val r7=l3.flatMap { line => line.split(" ") }
  				 .groupBy { word => word }
  				 .mapValues { list => list.length }
  				 .toArray
  				 .foreach{x=>println(x)}
                                                  //> (world,3)
                                                  //| (hello,4)
                                                  //| r7  : Unit = ()
  				 
  val r8=l3.flatMap { line => line.split(" ") }
  				 .groupBy { word => word }
  				 .map{x=>(x._1,x._2.length)}
                                                  //> r8  : scala.collection.immutable.Map[String,Int] = Map(world -> 3, hello ->
                                                  //|  4)
  //练习4:接着写,完成单词频次统计。不能使用list.length或list.size方法
  val r9=l3.flatMap { _.split(" ") }
  				 .map { (_,1) }
  				 .groupBy{_._1}
           .mapValues{_.map{_._2}.sum}            //> r9  : scala.collection.immutable.Map[String,Int] = Map(world -> 3, hello ->
                                                  //|  4)
           
           
  val r10=l3.flatMap { _.split(" ") }
  				 .map { (_,1) }
  				 .groupBy{_._1}
  				 .map{x=>(x._1,x._2.map{x=>x._2}.sum)}
                                                  //> r10  : scala.collection.immutable.Map[String,Int] = Map(world -> 3, hello -
                                                  //| > 4)
}