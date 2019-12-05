// file1.txt

5 3
1 6
4 9
8 3
4 7
5 6
3 2 

// result:

8 3
5 6
5 3
4 9
4 7
3 2
1 6

// code:

class SecondarySortKey(val first:Int,val second:Int) extends Ordered [SecondarySortKey] with Serializable {
def compare(other:SecondarySortKey):Int = {
    if (this.first - other.first !=0) {
         this.first - other.first 
    } else {
      this.second - other.second
    }
  }
}

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object SecondarySortApp {
  def main(args:Array[String]){
     val conf = new SparkConf().setAppName("SecondarySortApp").setMaster("local")
       val sc = new SparkContext(conf)
       val lines = sc.textFile("file:///usr/local/spark/mycode/rdd/examples/file1.txt", 1)
       val pairWithSortKey = lines.map(line=>(new SecondarySortKey(line.split(" ")(0).toInt, line.split(" ")(1).toInt),line))
       val sorted = pairWithSortKey.sortByKey(false)
       val sortedResult = sorted.map(sortedLine =>sortedLine._2)
       sortedResult.collect().foreach (println)
  }
}
