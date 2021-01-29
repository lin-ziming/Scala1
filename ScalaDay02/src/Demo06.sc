object Demo06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  //创建一个Tuple 元组。元组用于存数据和取数据
  val t1=(1,2,"hello","world")                    //> t1  : (Int, Int, String, String) = (1,2,hello,world)
  
  //通过._下标对元组取值。注意:元组的下标从1开始
  t1._3                                           //> res0: String = hello
  
  //练习1:取出t2中的数字4
  val t2=((1,2),(3,4))                            //> t2  : ((Int, Int), (Int, Int)) = ((1,2),(3,4))
  
  t2._2._2                                        //> res1: Int = 4
  
  //练习2:取出t3中的数字8
  val t3=(1,2,(3,4,(5,6,(7,Array(8,9)))))         //> t3  : (Int, Int, (Int, Int, (Int, Int, (Int, Array[Int])))) = (1,2,(3,4,(5,6
                                                  //| ,(7,Array(8, 9)))))
  
  t3._3._3._3._2(0)                               //> res2: Int = 8
  
  val m1=Map("tom"->23,"rose"->18,"jim"->25)      //> m1  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 18
                                                  //| , jim -> 25)
  
  val m2=m1.filter{case(k,v)=>v>18}               //> m2  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, jim -> 25)
                                                  //| 
  
  val m3=m1.filter{x=>x._2>18}                    //> m3  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, jim -> 25)
                                                  //| 
  
  //练习1:对m1,使用map方法映射。key不变，value+10。使用元组形式操作
  val m4=m1.map{x=>(x._1,x._2+10)}                //> m4  : scala.collection.immutable.Map[String,Int] = Map(tom -> 33, rose -> 28
                                                  //| , jim -> 35)
}