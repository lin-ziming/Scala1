/**
Array和List通用且重要的方法
1.max
2.min
3.sum
4.take
5.takeRight
6.drop
7.dropRight
8.head
9.last
10.intersect(交集)
11.union(并集)
12.diff(差集)
13.distinct
14.mkString
15.reverse(翻转)
16.filter
17.map
18.flatMap(扁平化映射)
19.reduce
20.sortBy
21.groupBy
22.foreach(遍历集合操作)

重中之重: ①filter  ②map  ③flatMap ④reduce ⑤sortBy ⑤groupBy
*/
object Demo04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //创建一个定长List并赋值
  val l1=List(1,2,3,4)                            //> l1  : List[Int] = List(1, 2, 3, 4)
  
  //创建一个变长List并赋值
  val l2=scala.collection.mutable.ListBuffer(1,2,3,4)
                                                  //> l2  : scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4)
  //通过下标操作List
  l1.apply(0)                                     //> res0: Int = 1
  l1(0)                                           //> res1: Int = 1
  
  //List和Array可以相互转换
  val a1=l1.toArray                               //> a1  : Array[Int] = Array(1, 2, 3, 4)
  val l3=a1.toList                                //> l3  : List[Int] = List(1, 2, 3, 4)
  
  val r1=l3.takeRight(1)                          //> r1  : List[Int] = List(4)
  
  //返回集合头元素,有别于take(1)
  val r2=l3.head                                  //> r2  : Int = 1
  //返回集合尾元素,有别于takeRight(1)
  val r3=l3.last                                  //> r3  : Int = 4
  
  val l4=List(1,2,3,4)                            //> l4  : List[Int] = List(1, 2, 3, 4)
  
  //map方法:映射方法。根据匿名函数将元素从原来的形式映射(变化)为新的形式,
  //并将映射后的数据返回到新的集合中
  
  val r4=l4.map { num => num*2 }                  //> r4  : List[Int] = List(2, 4, 6, 8)
  
  val r5=l4.map { num => num.toString }           //> r5  : List[String] = List(1, 2, 3, 4)
  
  
  val l5=List("tom M 23","rose F 18","jary M 25","jim M 30")
                                                  //> l5  : List[String] = List(tom M 23, rose F 18, jary M 25, jim M 30)
  
  //练习1:对l5映射，返回的新集合:List("tom","rose","jary","jim")
  val r6=l5.map { line => line.split(" ")(0) }    //> r6  : List[String] = List(tom, rose, jary, jim)
  
  //练习2:计算出l5中所有人的年龄之和
  val r7=l5.map { line => line.split(" ")(2).toInt }.sum
                                                  //> r7  : Int = 96
  //练习3:计算出l5中男性年龄的均值
  val r8=l5.filter { line => line.split(" ")(1)=="M" }
           .map { line => line.split(" ")(2).toInt }
                                                  //> r8  : List[Int] = List(23, 25, 30)
  val r9=r8.sum.toDouble/r8.length                //> r9  : Double = 26.0
  
  
  val l6=List("hello world","hello scala","hello 2008")
                                                  //> l6  : List[String] = List(hello world, hello scala, hello 2008)
  
  val r10=l6.map { line => line.split(" ") }      //> r10  : List[Array[String]] = List(Array(hello, world), Array(hello, scala),
                                                  //|  Array(hello, 2008))
  
  //flatMap扁平化映射
  val r11=l6.flatMap { line => line.split(" ") }  //> r11  : List[String] = List(hello, world, hello, scala, hello, 2008)
  
  val l7=List("1 2 5","3 4 8 9","6 7")            //> l7  : List[String] = List(1 2 5, 3 4 8 9, 6 7)
  
  //练习4:求出l7中数字的极差
  val r12=l7.flatMap { line => line.split(" ") }
  					.map { num => num.toInt }
                                                  //> r12  : List[Int] = List(1, 2, 5, 3, 4, 8, 9, 6, 7)
  					
  val r13=r12.max-r12.min                         //> r13  : Int = 8
  
  val l8=List(1,2,3,4)                            //> l8  : List[Int] = List(1, 2, 3, 4)
  
  //reduce:归约方法
  //底层迭代处理
  //第1次:a=1 b=2 result=3
  //第2次:a=3 b=3 result=6
  //第3次:a=6 b=4 result=10
  val r14=l8.reduce{(a,b)=>a+b}                   //> r14  : Int = 10
  
  //练习5:求出l8中的阶乘结果
  val r15=l8.reduce{_*_}                          //> r15  : Int = 24
  
  
  val l9=List(2,1,5,4,3)                          //> l9  : List[Int] = List(2, 1, 5, 4, 3)
  //练习6:使用reduce求出l9中的最大值。不能使用max
  //第1次:a=2 b=1  result=2
  //第2次:a=2 b=5  result=5
  //第3次:a=5 b=4  result=5
  //...
  val r16=l9.reduce{(a,b)=>if(a>b) a else b}      //> r16  : Int = 5
  
  //sortBy 排序:根据指定的匿名函数条件实现排序。并将排序后的结果返回到新的集合中
  //下面表示按数字做升序排序
  val r17=l9.sortBy { num => num }                //> r17  : List[Int] = List(1, 2, 3, 4, 5)
  //降序排序。注意负号前有空格,负号只用作用于数值类型
  val r18=l9.sortBy { num => -num }               //> r18  : List[Int] = List(5, 4, 3, 2, 1)
  
  val r19=l9.sortBy { num => num }.reverse        //> r19  : List[Int] = List(5, 4, 3, 2, 1)
  
  
  val l10=List("bj 6500","sh 7000","bj 5000","bj 10000","sh 6000")
                                                  //> l10  : List[String] = List(bj 6500, sh 7000, bj 5000, bj 10000, sh 6000)
  
  //练习7:基于l10,返回按薪资降序排序的结果
  val r20=l10.sortBy { line => -line.split(" ")(1).toInt }
                                                  //> r20  : List[String] = List(bj 10000, sh 7000, bj 6500, sh 6000, bj 5000)
  
  //练习8:基于l10,返回按地区降序排序的结果
  val r21=l10.sortBy { line => line.split(" ")(0) }.reverse
                                                  //> r21  : List[String] = List(sh 6000, sh 7000, bj 10000, bj 5000, bj 6500)
  
  //练习9:求出北京地区薪资最高的前两名的薪资差
  val r22=l10.filter { line => line.split(" ")(0)=="bj" }
  					 .map { line => line.split(" ")(1).toInt }
  					 .sortBy{salary=> -salary}
  					 .take(2) //> r22  : List[Int] = List(10000, 6500)
  val r23=r22.max-r22.min                         //> r23  : Int = 3500
}