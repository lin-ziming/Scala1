
/**
 * 知识点
 * 1.case class 是scala的样例类
 * 2.case class必须显式的声明一个主构造器,可以通过主构造器传参
 * 3.case class主构造器中的形参名就是类的成员名
 * 4.case class会自动为成员名生成get和set方法
 * 5.case class会自动创建一个空的辅助构造器
 * 6.case class会自动实现成员名的toString方法。便于打印输出等场景
 * 7.case class会自动混入序列化特质,即省略了with Serializable
 * 8.case class不需要new就可以创建类的对象
 * 
 * 综上,后续在开发过程中，习惯上使用case class来封装bean
 * 
 * 
 */
case class Item(title:String,price:Double){
  
}