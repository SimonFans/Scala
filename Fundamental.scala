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



