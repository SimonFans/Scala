// file1.txt

129
54
167
324
111
54
26
697
4856
3418

// file2.txt

5
329
14
4567
2186
457
35
267

// MaxAndMin.scala

import org.apache.spark.{SparkConf, SparkContext}
object MaxAndMin {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MaxAndMin").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val lines = sc.textFile("/user/xzhao/test2", 2)
    val result = lines.filter(_.trim().length>0).map(line => ("key",line.trim.toInt)).groupByKey().map(x => {
      var min = Integer.MAX_VALUE
      var max = Integer.MIN_VALUE
      for(num <- x._2){
        if(num>max){
          max = num
        }
        if(num<min){
          min = num
        }
      }
      (max,min)
    }).collect.foreach(x => {
      println("max\t"+x._1)
      println("min\t"+x._2)
    })
  }
}





