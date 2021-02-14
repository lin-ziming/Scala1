package cn.tedu.item

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression

/**
 * 处理data目录下的lritem.txt
 * Y   X1 X2
 * 100|5 1000
		75|7 600
		80|6 1200
		70|6 500
 *
 * 建立的模型方程:Y=β1X1+β2X2+β0
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf().setMaster("local").setAppName("item")
    
    val sc=new SparkContext(conf)
    
    val data=sc.textFile("e://data/lritem.txt")
    
    //第一步:为了建模需要,RDD[line:String]->RDD[(X1:Int,X2:Int,Y:Int)]
    val r1=data.map { line =>
       val arr=line.split("\\|")
       val Y=arr(0).toInt
       val X1=arr(1).split(" ")(0).toInt
       val X2=arr(1).split(" ")(1).toInt
       (X1,X2,Y)
    }
    
    //第二步:RDD[(X1:Int,X2:Int,Y:Int)]->DataFrame数据表(X1列,X2列,Y列)
    //创建SparkSql对象。可以将RDD转变为DataFrame
    val sqc=new SQLContext(sc)
    //toDF()用于指定列名。列的个数与顺序要和RDD中的元组保持一致
    val df=sqc.createDataFrame(r1).toDF("X1","X2","Y")
    
    //第三步:DataFrame数据表(X1列,X2列,Y列)->DataFrame数据表(X1列,X2列,Y列,Vector(X1列,X2列))
    //setInputCols:指定自变量列
    //setOutputCol:指定所有自变量列的列名
    val vectorAss=new VectorAssembler().setInputCols(Array("X1","X2"))
                                       .setOutputCol("features")
    
    val dfVector=vectorAss.transform(df)  
    
    //第四步:建模
    //setFeaturesCol:指定自变量列
    //setLabelCol:指定因变量列
    //setFitIntercept:指定是否需要计算截距项系数 β0。true表示计算。
    //fit:代入数据集建模计算
    val model=new LinearRegression().setFeaturesCol("features")
                                    .setLabelCol("Y")
                                    .setFitIntercept(true)
                                    .fit(dfVector)
                                    
    //获取自变量系数，β1，β2
    //Y=-6.497X1+0.0163X2+106.368                              
    val coef=model.coefficients   
    //获取截距项系数，β0
    val intercept=model.intercept
    
    //transform:模型提供的预测方法，下面的代码表示回代原数据集做预测
    //transform方法要求传入的数据类型:DataFrame数据表(X1列,X2列,Y列,Vector(X1列,X2列))
    val result=model.transform(dfVector)
    
    result.show
    
    //第五步:利用模型实现对数据的预测
    //给定一组待测数据 X1=10,X2=500,预测Y=?
    //RDD[(X1=10,X2=500,0)]->DataFrame(X1列,X2列,Y列)
    //->DataFrame(X1列,X2列,Y列,Vector(X1列,X2列))->model.transform()
    val r2=sc.parallelize(List((10,500,0)))
    val df2=sqc.createDataFrame(r2).toDF("X1","X2","Y")
    val df2Vector=vectorAss.transform(df2)
    val result02=model.transform(df2Vector)
    
    result02.show
    
    //练习1:利用模型预测出data目录的testitem.txt中各组数据的Y值
    /*
     * 10 500
			 12 650
			 8 700
			 15 900
		 *
		 *
     */
    val r3=sc.textFile("e://data/testitem.txt")
             .map { line =>line.split(" ").map { num => num.toInt } }
             .map { arr =>(arr(0),arr(1),0) }
             
    val df3=sqc.createDataFrame(r3).toDF("X1","X2","Y")
    val df3Vector=vectorAss.transform(df3)
    
    val result03=model.transform(df3Vector)
    
    result03.show
    
  }
  
}