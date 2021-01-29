
/**
 * 知识点
 * 1.scala通过class来定义一个类
 * 2.scala类可以拥有成员变量和成员方法
 * 3.scala类通过new关键字来创建类的实例对象
 * 4.默认的访问权限是public(什么都不加)。此外可以通过private或protected修饰。作用同java
 * 5.scala类的构造器可以定义1个主构造器和多个辅助构造器
 * 6.主构造器只能有1个,在类上声明
 * 7.辅助构造器可以有多个,在类中声明。形式:def this(参数...){}。
 * 此外，辅助构造器中必须调用本类的其他构造器。一般都是调用主构造器
 * 8.scala也支持重写与重载，定义同java
 * 9.scala也支持继承,机制同java
 * 10.scala在重写方法时,如果重写的是一个普通方法,则需要加override关键字
 * 如果重写的是一个抽象方法,则不需要加override
 * 11.scala也有抽象类，机制同java。可以在抽象类中定义抽象方法
 * 12.scala定义抽象方法，特点:没有方法体。比如:def makeNote(note:String):String
 * 13.scala有特质 trait,等价于java的接口
 * 14.scala通过with关键字来混入特质。scala是单继承,多混入
 * 15.scala的trait特质中,既可以定义抽象方法,也可以定义普通方法。这样设计可以达到多继承的效果
 * 16.scala要求,在继承或混入时，必须有并且仅有1个extends关键字
 * 17.scala没有static关键字,所以要实现静态机制需要使用object单例对象类来完成。
 * 在object单例对象类中,所有的成员变量和成员方法都是静态的。
 * 18.object单例对象类不能new
 * 19.main是静态方法,必须写在object中,运行启动方式:双击main->右键->run as->scala application
 * 20.如要要为一个class添加静态成员和方法,要求:在class的同文件下,创建一个同名的object。
 * 此时class和object产生了一个伴生关系(绑定在一起了)
 * class称为是object伴生类
 * object称为是class的伴生对象
 * 综上,如果要为一个class使用静态机制。就是在同文件下创建一个同名的object单例对象类即可
 */
class People(v1:String,v2:Int) {
  
  private var name=v1
  private var age=v2
  
  
  def this(v1:String){
    this(v1,0)
  }
  
  def this(v2:Int){
    this("",v2)
  }
  
  def this(){
    this("",0)
  }
  
  def setName(name:String)={
    this.name=name
  }
  
  def getName()={
    this.name
  }
  
  def setAge(age:Int)={
    this.age=age
  }
  
  def getAge()={
    this.age
  }
  
  
   def say()={
    println("hello")
  }
}

object People{
  
  
  def run()={
    println("123")
  }
}