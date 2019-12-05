// Movie
// MovieID::Title::Genres

1::Toy Story (1995)::Animation|Children's|Comedy
2::Jumanji (1995)::Adventure|Children's|Fantasy
3::Grumpier Old Men (1995)::Comedy|Romance
4::Waiting to Exhale (1995)::Comedy|Drama
5::Father of the Bride Part II (1995)::Comedy
6::Heat (1995)::Action|Crime|Thriller
7::Sabrina (1995)::Comedy|Romance
8::Tom and Huck (1995)::Adventure|Children's
9::Sudden Death (1995)::Action
10::GoldenEye (1995)::Action|Adventure|Thriller

// Rating
// UserID::MovieID::Ratings::Timestamp

1::1193::5::978300760
1::661::3::978302109
1::914::3::978301968
1::3408::4::978300275
1::2355::5::978824291
1::1197::3::978302268
1::1287::5::978302039
1::2804::5::978300719
1::594::4::978302268
1::919::4::978301368
1::595::5::978824268
1::938::4::978301752
1::2398::4::978302281
1::2918::4::978302124
1::1035::5::978301753
1::2791::4::978302188
1::2687::3::978824268

// code:

import org.apache.spark._ 
import SparkContext._ 
object SparkJoin { 
  def main(args: Array[String]) { 
    if (args.length != 3 ){ 
      println("usage is WordCount <rating> <movie> <output>")      
      return 
    } 
   val conf = new SparkConf().setAppName("SparkJoin").setMaster("local")
   val sc = new SparkContext(conf)  
   // Read rating from HDFS file 
   val textFile = sc.textFile(args(0)) 
   val rating = textFile.map(line=>{val fields=line.split("::")
                 (fields(1).toInt,fields(2).toDouble)})
   val movieScores = rating.groupByKey().map(data=>{val avg=data._2.sum/data._2.size
                     (data._1,avg)})
   
   // Read movie from HDFS file
   
  val movies = sc.textFile(args(1))
  val moviesKey=movies.map(line=>{val fields=line.split("::") 
                 (fields(0).toInt,fields(1))}).keyBy(tup=>tup._1)
  
  // join
  val result = movieScores.keyBy(tup=>tup._1)
               .join(moviesKey)
               .filter(f=>f._2._1._2>4.0)
               .map(f=>(f._1,f._2._1._2,f._2._2._2))
  result.saveAsTextFile(args(2))
   }
  }
               
              
