/**
知识点
1.eclipse调字体:window->preference->color and font->下拉basic—>编辑text font 更改字体样式和大小
2.scala work sheet 是eclipse提供的交互式编译模式。左侧写完代码后,保存,右侧会显示结果。所以便于练习测试
3.保存的快捷键:ctrl+s
4.scala创建一个对象时，分为变量和常量
5.变量的声明用var,赋值后可以修改
6.常量的声明用val,赋值后不可以修改
7.scala每行语句以换行符为结束标识,不需要加;结尾
8.scala如果在一行中写多条语句,此时必须使用;隔开  比如:var v1=100;v1=200
9.scala不需要显式的声明对象类型,scala可以根据赋值结果,自动推断类型
10.scala可以显式的声明对象类型。形式: val v5:String="hello 123"
11.scala的泛型使用[泛型类型],不同于java的<泛型类型>
12.scala中,没有基本类型,创建都是类的对象。从这一点来看,scala的面向对象要比java彻底。
13.scala的类型转换,形式固定:to目标类型
*/

object Demo01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  println("hello scala")                          //> hello scala
  
  //声明一个变量
  var v1=100;v1=200                               //> v1  : Int = 200
  
  //声明一个常量
  val v2=100                                      //> v2  : Int = 100
  
  val v3="hello world"                            //> v3  : String = hello world
  
  val v4=Array(1,2,3,4)                           //> v4  : Array[Int] = Array(1, 2, 3, 4)
  
  val v5:String="hello 123"                       //> v5  : String = hello 123
  
  v2.+(300)                                       //> res0: Int = 400
  
  val v6="100"                                    //> v6  : String = 100
  
  v6.toInt                                        //> res1: Int = 100
  v6.toDouble                                     //> res2: Double = 100.0
  
  val v7=100                                      //> v7  : Int = 100
  v7.toString                                     //> res3: String = 100
  
  
  
  
  
}