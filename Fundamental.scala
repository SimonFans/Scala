// scala 构造器（一个主构造器和若干辅助构造器), 辅助构造器名称为this,每个辅助构造器都必须调用一个此前已经定义的辅助构造器或者主构造器

//（1） 辅助构造器必须先调用主构造器
/*
class Counter{
  private var value = 0
  private var name =""
  private var mode = 1
  def this(name:String){  //定义辅助构造器，因为这是第一个辅助构造器，所以只能先调主
    this()  // 调用主构造器
    this.name=name
  }
  def this(name:String, mode:Int){ // 第二个辅助构造器
    this(name)   // 调用第一个辅助构造器
    this.mode=mode
  }
  def increment(step:Int)={
    value+=step
  }
  def current():Int={value}
  def Info={
    printf("Name is %s, Mode is %d \n", name, mode)
  }
}

object MyCounter{
  def main(args: Array[String]): Unit = {
    val counter1 = new Counter
    counter1.Info
    val counter2 = new Counter("Mark")
    counter2.increment(3)
    counter2.Info
    printf("current value is %d ",counter2.current)
  }
}
*/

// (2) 直接定义参数在主构造器名称后面，不需要放在括号里
/*
class Counter(val name:String, val mode:Int){
  private var value = 0
  def increment(step:Int)={
    value+=step
  }
  def current():Int={value}
  def Info={
    printf("Name is %s, and Mode is %d \n",name, mode)
  }
}

object MyCounter{
  def main(args: Array[String]): Unit = {
    val myCounter = new Counter("Simon",1)
    myCounter.Info
    myCounter.increment(3)
    printf("Current value is %d \n",myCounter.current)
  }
}
*/

/* 单例对象: no class keyword, object里的方法，变量都是静态的，相当于可以存值累加

object Person{
  private var lastId=0
  def newPersonId()={
    lastId+=1
    lastId
  }
}

object singleObject {
  def main(args: Array[String]): Unit = {
    printf("The first person Id is : %d. \n",Person.newPersonId())
    printf("The second person Id is : %d. \n",Person.newPersonId())
    printf("The third person Id is : %d. \n",Person.newPersonId())
  }
}
*/

// 伴生对象：class name and object name are same, must be in the same file, 方法相互调用。
/*
class Person{
  private val id = Person.newPersonId()
  private var name = ""
  def this(name: String){
    this()  // 调用主构造器
    this.name=name
  }
  def Info: Unit ={
    printf("The Id of %s is %d \n",name,id)
  }
}

object Person{
  private var lastId=0
  def newPersonId() = {
    lastId += 1
    lastId
  }
  def main(args: Array[String]): Unit = {
    val person1 = new Person("Ximeng")
    person1.Info
    val person2 = new Person("Simon")
    person2.Info
  }
}
*/

// Apply method, that's by default, 自动被调用
/*
object TestApply{
  def apply(para1:String,para2:String):String={
    println("Apply method called")
    para1 + "and" + para2
  }
}

object test{
  def main(args: Array[String]): Unit = {
    val group = TestApply("feifei","beibei")
    println(group)
  }
}
*/

// Apply method (伴生类 & 伴生对象)
/*
class TestApplyClassObject{

}

class ApplyTest{
  def apply() = println("apply method in class is called! ")
  def greeting={
    println("greeting method in class is called")
  }
}

object ApplyTest{
  def apply()={
    println("apply method in object is called! ")
    new ApplyTest()
  }
}

object TestApplyClassObject{
  def main(args: Array[String]): Unit = {
    val a = ApplyTest() // 调用伴生对象中的apply method
    a.greeting
    a() // 调用伴生类中的apply method, 必须先产生个实例对象，再加个圆括号
  }
}

// 举例： val a =Array(1,2,3), 因为不用 new Array, 所以先调用Array伴生对象的apply方法，然后圆括号执行Array类的update方法
*/


// 继承
/*
abstract class car{    // abstract class, other class will inherit/ expand from this class
  val carBrand: String    // 不给任何值叫抽象字段
  def info()              // 不写方法体叫抽象方法
  def greeting()={   // 不是抽象方法
    println("Welcome to my car")
  }
}

class BMWCar extends car {
  override val carBrand = "BMW" // 重写父类字段，必须用override，否则报错
  def info()={            // 重写父类抽样方法， 可用也可不用加override
    printf("This is %s car \n",carBrand)
  }
  override def greeting()={  // 重写父类的非抽象方法，必须用override
    println("Welcome to BMW !")
  }
}

class SUVCar extends car {
  override val carBrand = "SUV"
  def info()={
    printf("This is %s car \n",carBrand)
  }
  override def greeting()={
    println("Welcome to SUV !")
  }
}

object MyCar{
  def main(args: Array[String]): Unit = {
    val car1 = new BMWCar()
    val car2 = new SUVCar()
    car1.greeting()
    car1.info()
    car2.greeting()
    car2.info()
  }
}
*/

// trait
/*
trait CarId{
  var id:Int    // 抽象字段
  def currentId():Int  // 抽象方法
}

trait CarGreeting{
  def greeting(msg:String): Unit ={
    println(msg)
  }
}
class BYDCar extends CarId with CarGreeting { // extends.. with ..加入更多trait
  override var id = 10000
  override def currentId(): Int = {id+=1; id}
}

class BMWCar extends CarId with CarGreeting{
  override var id = 20000
  override def currentId(): Int = {id+=1; id}
}

object MyCar{
  def main(args: Array[String]): Unit = {
    val car1 = new BYDCar()
    val car2 = new BMWCar()
    car1.greeting("Welcome to my first car!")
    printf("My first car id is %d \n",car1.currentId())
    car2.greeting("Welcome to my second car!")
    printf("My second car id is %d",car2.currentId())
  }
}
*/

// Match的用法

object testMatch{
  def main(args: Array[String]): Unit = {
    val colorNum = 5
    val colorStr = colorNum match {
      case 1 => "Red"
      case 2 => "Green"
      case 3 => "Yellow"
      case _ => "Not found"
    }
    println(colorStr)
  }
}

object testMatch_Variable{
  def main(args: Array[String]): Unit = {
    val colorNum = 5
    val colorStr = colorNum match {
      case 1 => "Red"
      case 2 => "Green"
      case 3 => "Yellow"
      case unexpected => unexpected+" is not found"
    }
    println(colorStr)
  }
}


object testMatch_Type{
  def main(args: Array[String]): Unit = {
    for (elem <- List(9,12.3,"Spark","Hadoop",'Hello)) {
      val str = elem match {
        case i:Int => i + " is an int value "
        case d:Double => d+ " is a double value "
        case "Spark" => "Spark is found "
        case s: String => s + " is string type "
        case _ => "This is a unexpected value "
      }
      println(str)
    }
  }
}

object testMatch_logic{
  def main(args: Array[String]): Unit = {
    for (elem <- List(1,2,3,4,5)){
      elem match {
        case _ if (elem%2==0) => println(elem + " is an even number")
        case _  => println(elem + " is an odd number")
      }
    }
  }
}

case class Car (brand:String, price:Int)

object testMatch_caseClass{
  def main(args: Array[String]): Unit = {
    val car1 = new Car("A",10)
    val car2 = new Car(brand = "B",price = 20)
    val car3 = new Car(brand = "C",price = 50)
    for (car <- List(car1,car2,car3)){
      car match{
        case Car ("A",10) => println("This is Car1")
        case Car ("B",20) => println("This is Car2")
        case Car(brand,price) => printf("Brand: %s, Price: %d",brand,price)
      }
    }
  }
}

// Option type (Some or None are subtype of Option)
// If has value returned, then show Some(), otherwise show None
// Sometimes we want to show some meaningful info, so we use getOrElse() method
// Option 返回的是一个集合，可以是None，一个单值，或者多个值，所以可以理解返回的是一个集合
object testMatch_Option{
  def main(args: Array[String]): Unit = {
    val books = Map("hadoop"-> 5, "spark"-> 10)
    println(books.get("hadoop")) // return Some(5)\
    println(books.get("hive"))
    val sales = books.get("hive")
    println(sales.getOrElse("No such book"))
    books.get("hive").foreach(println)
  }
}


// 匿名函数

object test_function{
  def main(args: Array[String]): Unit = {

    val myNumFunction :Int => Int = (num:Int) => num*2
    val myNumFunction1 = (num:Int) => num*2
    val myNumFunction2 :Int => Int = (num) => num*2
    println(myNumFunction(3))
    println(myNumFunction1(5))
    println(myNumFunction2(10))
  }
}

// 闭包:是一个函数，反映了一个从开放到封闭的过程，每个闭包都会访问闭包创建时活跃的more变量

object test_closed_package{
  def main(args: Array[String]): Unit = {
    var more = 1
    val addMore = (x:Int) => x+ more
    println(addMore(10))
    more = 5
    println(addMore(20))
  }
}

// 占位符的用法

object test_Placeholder{
  def main(args: Array[String]): Unit = {
    val numList = List(-3,-5,1,6,9)
    println(numList.filter(_ > 0))
    val f = (_:Int) + (_:Int)
    println(f(5,10))
  }
}

// 集合
// 1. Map & flatMap. Map is 1:1, flatMap is 1 to n. flatMap对每个输入都会返回一个集合（而不是一个元素）, 最后多个集合成为一个集合。
// 2. filter
// 3. reduce <reduceLeft, reduceRight>
// 4  fold 操作需要提供一个初始值，然后和reduce相似，默认从左至右一次操作

object collection_map{
  def main(args: Array[String]): Unit = {
    val books = List("Hadoop","Hive","Hbase")
    val b_map=books.map(s=>s.toUpperCase())
    println("Map: "+b_map)
    val b_flatMap=books.flatMap(s=>s.toList)
    println("flatMap: "+b_flatMap)
  }
}


object collection_filter{
  def main(args: Array[String]): Unit = {
    val university = Map("Dallas"->"UTD","Seattle"->"Washington University")
    val dallas = university.filter(x=>x._2=="UTD")
    val dallas1 = university.filter(x=>x._2 contains "UTD")
    val dallas2 = university.filter(x=>x._2 startsWith "U")
    println(dallas)
    println(dallas1)
    println(dallas2)
  }
}


object collection_reduce{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    val left = list.reduceLeft(_+_)
    val right = list.reduceRight(_+_)
    println(left)
    println(right)
  }
}

object collection_fold{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    val res = list.fold(10)(_*_)
    println(res)
  }
}






