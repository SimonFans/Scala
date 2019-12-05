// file1

33
37
12
40

// file2

4
16
39
5

// file3

1
45
25

// output:

1     1
2     4
3     5
4     12
5     16
6     25
7     33
8     37
9     39
10    40
11    45

// code:
// partitionBy(new hashPartitioner(1)) 就是在做sortByKey()前把原先的三个分区变为1个，以防止排序错误

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.HashPartitioner
object FileSort {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("FileSort")
        val sc = new SparkContext(conf)
        val dataFile = "/user/xzhao/test3"
        val lines = sc.textFile(dataFile,3)
        var index = 0
        val result = lines.filter(_.trim().length>0).map(n=>(n.trim.toInt,"")).partitionBy(new HashPartitioner(1)).sortByKey().map(t => {
　　　　　　index += 1
            (index,t._1)
        })
        result.saveAsTextFile("/user/xzhao/result")
    }
}
