// The code implements counting the number of words under a certain directory

import java.io.File
import scala.io.Source

object WordCount{
  def main(args: Array[String]): Unit = {
    val dirfile = new File("/Users/xzhao/Documents/scala_test")
    val files = dirfile.listFiles()  // file address collection: [Ljava.io.File;@fad74ee
    for (file <- files) println(file) // each file path: /Users/xzhao/Documents/scala_test/text
    val listFiles = files.toList
    val wordsMap = scala.collection.mutable.Map[String,Int]()
    listFiles.foreach(file => Source.fromFile(file).getLines().foreach(line => line.split(" ")
      .foreach(word => {
        if (wordsMap.contains(word)){
          wordsMap(word)+=1
        }else{
          wordsMap+=(word->1)
        }

      } )))
    println(wordsMap)
    for ((key,value) <- wordsMap) println(key+": "+value)
  }
}
