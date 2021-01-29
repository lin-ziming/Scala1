object Demo08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val p1=new People("tom",23)                     //> p1  : People = People@7a79be86
  
  p1.getName()                                    //> res0: String = tom
  p1.getAge()                                     //> res1: Int = 23
  
  p1.say()                                        //> hello
  
  val p2=new People("tom")                        //> p2  : People = People@39ba5a14
  
  p2.getName()                                    //> res2: String = tom
  p2.getAge()                                     //> res3: Int = 0
  
  val p3=new People(23)                           //> p3  : People = People@511baa65
  
  val p4=new People                               //> p4  : People = People@340f438e
  
  Util.speak()                                    //> hello
  
  People.run()                                    //> 123
  
  val i1=Item("huawei",3999)                      //> i1  : Item = Item(huawei,3999.0)
  
  i1.title                                        //> res4: String = huawei
  i1.price                                        //> res5: Double = 3999.0
  
  val i2=Item                                     //> i2  : Item.type = Item
  
  println(i1)                                     //> Item(huawei,3999.0)
  
  
  //Option类有两个子类:Some和None
  //可以通过getOrElse来进行取值操作,如果是None,则返回指定的默认值
  def f1(a:Int,b:Int)={
  	if(b!=0){
  		Some(a/b)
  	}else{
  		None
  	}
  
  }                                               //> f1: (a: Int, b: Int)Option[Int]
  
  f1(4,2)                                         //> res6: Option[Int] = Some(2)
  
  f1(4,0)                                         //> res7: Option[Int] = None
  
  f1(4,2).getOrElse(0)                            //> res8: Int = 2
  f1(4,0).getOrElse(1000)                         //> res9: Int = 1000
  
}