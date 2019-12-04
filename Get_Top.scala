// File1.txt

1,1768,50,155 
2,1218,600,211 
3,2239,788,242 
4,3101,28,599 
5,4899,290,129 
6,3110,54,1201
7,4436,259,877 
8,2369,7890,27

// File2.txt

100,4287,226,233 
101,6562,489,124 
102,1124,33,17 
103,3267,159,179 
104,4569,57,125
105,1438,37,116

// TopN.scala

import org.apache.spark.{SparkConf, SparkContext}
object TopN {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TopN").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val lines = sc.textFile("/user/xzhao/test1")
    var num = 0;
    val result = lines.filter(line => (line.trim().length > 0) && (line.split(",").length == 4))
      .map(_.split(",")(2))
      .map(x => (x.toInt,""))
      .sortByKey(false)
      .map(x => x._1).take(5)
      .foreach(x => {
        num = num + 1
        println(num + "\t" + x)
      })
  }
}

// download sbt and unzip the sbt package, create a path src/main/scala, move the TopN.scala under src/main/scala

// create sparksample.sbt, check spark version using spark-submit --version, current spark version is 2.0.1, scala version is 2.11.0

name := "Spark Sample"
version := "2.0.1"
scalaVersion := "2.11.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.1"

// run sbt compile then sbt package, then you will see the jar file under /target

drwxrwxr-x 2 xzhao xzhao 4096 Dec  3 05:58 get_top_value
drwxrwxr-x 3 xzhao xzhao 4096 Dec  3 05:57 project
-rw-rw-r-- 1 xzhao xzhao  135 Dec  3 06:05 sparksample.sbt
drwxrwxr-x 3 xzhao xzhao 4096 Dec  3 05:51 src
drwxrwxr-x 4 xzhao xzhao 4096 Dec  3 06:00 target

[xzhao@xxxxxx spark_example]$ ll target/scala-2.11/
drwxrwxr-x 2 xzhao xzhao 4096 Dec  3 06:07 classes
-rw-rw-r-- 1 xzhao xzhao 6501 Dec  3 06:07 spark-sample_2.11-2.0.1.jar
drwxrwxr-x 3 xzhao xzhao 4096 Dec  3 06:00 update

// run spark-submit, class part put <object name>

spark-submit --class "TopN" target/scala-2.11/spark-sample_2.11-2.0.1.jar

