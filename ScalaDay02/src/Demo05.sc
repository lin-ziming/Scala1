object Demo05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  //创建一个定长Map，形式:Map(key->value,key->value...)
  val m1=Map("tom"->23,"rose"->18,"jim"->25)      //> m1  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 18
                                                  //| , jim -> 25)
  
  //创建一个变长Map,可以追加kv对
  val m2=scala.collection.mutable.Map("tom"->23,"rose"->18)
                                                  //> m2  : scala.collection.mutable.Map[String,Int] = Map(rose -> 18, tom -> 23)
                                                  //| 
  //通过key获取对应的value,apply方法可以省略
  m1.apply("tom")                                 //> res0: Int = 23
  
  m1("tom")                                       //> res1: Int = 23
   
  //对变长Map追加kv对
  m2+=("jim"->25,"jary"->30)                      //> res2: Demo05.m2.type = Map(jary -> 30, jim -> 25, rose -> 18, tom -> 23)
  
  //获取Map中的所有key和value,返回一个迭代器。迭代器也是一种集合类型
  //如果要进行操作,可以转变为List或Array再进行操作。因为List和Array的方法是最全的
  val r1=m1.keys.toArray                          //> r1  : Array[String] = Array(tom, rose, jim)
  val r2=m1.values.toArray                        //> r2  : Array[Int] = Array(23, 18, 25)
  
  //通过for循环遍历Map
  for(i<-m1){
  	println(i)                                //> (tom,23)
                                                  //| (rose,18)
                                                  //| (jim,25)
  }
  
  for((k,v)<-m1){
  	println(k)                                //> tom
                                                  //| rose
                                                  //| jim
  }
  
  val m3=Map("book"->10,"gun"->100,"ipad"->1000)  //> m3  : scala.collection.immutable.Map[String,Int] = Map(book -> 10, gun -> 10
                                                  //| 0, ipad -> 1000)
  
  val m4=for((k,v)<-m3)yield{(k,v*0.9)}           //> m4  : scala.collection.immutable.Map[String,Double] = Map(book -> 9.0, gun -
                                                  //| > 90.0, ipad -> 900.0)
  
  //filter对Map实现过滤
  val m5=m1.filter{case(k,v)=>v>18}               //> m5  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, jim -> 25)
                                                  //| 
  
  //map对Map实现映射
  val m6=m1.map{case(k,v)=>(k,v+10)}              //> m6  : scala.collection.immutable.Map[String,Int] = Map(tom -> 33, rose -> 28
                                                  //| , jim -> 35)
  
  //对Map类型映射时，如果只对value做映射，key不变。此时可以mapValues
  val m7=m1.mapValues { v => v+10 }               //> m7  : scala.collection.immutable.Map[String,Int] = Map(tom -> 33, rose -> 28
                                                  //| , jim -> 35)
}