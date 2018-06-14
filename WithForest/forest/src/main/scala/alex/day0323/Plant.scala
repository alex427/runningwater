package alex.day0323

import java.io.IOException


//1.自动生成时, 这里没有public
class Plant (val name:String, val home:String, val func:Int=>Int){

    //实验: 主构造器启动时, class body中的所有statement语句都会执行
    try {
        println("read files from disk . . .")
        throw new IOException("io exception")
    } catch {
        case e: NullPointerException =>println("printer Exception : "+ e)
        case e: IOException =>println("printer IOException : "+ e)
    } finally {
        println("finally block is working....")
    }


    //var 属性, 可变
    var colour:String = "green"
    //val 属性, 不可变, 相当于Java中final修饰的属性
    val cell:String = "celled"

    //访问权限
    //private修饰的属性, 在client中无法直接访问
    private val root:String = "roots"
    private var leaf:String = "leaf"

    //对象私有字段
    private[this] val branch:String = "branches"
    private[this] var trunk:String = "one trunk"

    //构造器
    //用this关键字定义辅助构造器
    def this(colour: String, name: String, home: String, func:Int=>Int){
        //每个辅助构造器必须以主构造器或其他的辅助构造器的调用开始
        this(name, home,func)
        println("auxiliary constructor is working now...")
        this.colour = colour
    }

    def this(height:Int, colour: String, name: String, home: String, func:Int=>Int){
        //每个辅助构造器必须以主构造器或其他的辅助构造器的调用开始
        this(colour,name, home,func)
        println(height)
    }

    //方法
     def abosorb( water:String ):String = {
        var production:String = "mositure"
        production
    }

    private def synthesis( sun:String ):String = {
        var production:String = "oxgen"
        production
    }

    def callObject(): Unit ={
        //类和伴生对象之间可以互相访问对方的属性, 哪怕是private的, 区别是伴生对象的属性和方法并不在类的scope中, 因此需要用对象调用一下.
        //如下:
        println(Plant.myownPros)
        println(Plant.myPros)
        println(Plant.myMethod("cat"))
        println(Plant.myownMethod("dog"))
    }


}

//同名的伴生对象可以定义同一份source file中
object  Plant {

    private val myownPros = "it is a plant"
    val myPros = "it is a plant"

    def main(args: Array[String]): Unit = {

        val func2 = (y:Int)=>{
            println("func is working now")
            y
        }

        //val plant = new Plant("aaa","aadd",func2)
        val plant3 = new Plant(202,"black","aaa","aadd",func2)
    }

    private def myownMethod(x:String):Int = {
        if (x.equals("cat")) 99 else 66
    }

    def myMethod(x:String):Int = {
        if (x.equals("dog")) 999 else 666
    }
}