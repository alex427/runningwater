package alex.chapter12

/**
 * Created by zhiguang on 2018/4/7.
 */
object HighOderFunc {

    def main(args: Array[String]) {
        def quarterValue( f:(Double)=>Double ) = f(0.25)

        import scala.math._
        val fa = quarterValue( ceil _ )
        val fb = quarterValue( sqrt _ )

        val hf1 = quarterValue _
        println(fa)
        println(fb)
        println(hf1.getClass) //chapter12.HighOderFunc$$anonfun$3

        def produceFunc(weight:Double) = (x:Double)=> weight*x

        val fc = produceFunc(3)
        println(fc)    //<function1>
        println(fc.getClass)  //chapter12.HighOderFunc$$anonfun$mulBy$1$1
        println(fc(20))

        //val easyFunc = 3 * (x:Double)  错误, 必须是下面这种下划线的
        val easyFunc2 = 3 * (_:Double)
        println( easyFunc2(3.3) )  //9.899999999999999

        //打印9层三角形
        (1 to 9).map( "*"* _ ).map( println(_) )

        //排序
        "Francis is on the way to school.".split(" ").sortWith(_.length > _.length).map(println(_))

        //闭包
        val fd = produceFunc(5)
        val fe =produceFunc(8)
        val rr1 = fd(10)
        val rr2 = fe(15)
        println(rr1)

        //柯理化
        //1. 柯理化的完整写法
        def fn3(x:Int) = (y:Int) => x*y
        println(fn3(5)(12))
        //2. 柯理化的精简写法
        def fn4(x:Int)(y:Int) = x*y
        //下面的写法是错误的
        //def fn4(x:Int)(y:Int) => x*y
        //val fn5 = (x:Int)(y:Int) => x*y

        val a = Array("Jim","Jack")
        val b = Array("jim","jack")
        val c = Array(33,55)
        a.corresponds(b)(_.equalsIgnoreCase(_))
        //x是a的元素, y是b的元素
        //corresponds的第二参数是一个函数, 称为前提函数; 整体表现为柯理化的过程
        a.corresponds(b)( (x:String,y:String)=>x.equalsIgnoreCase(y) )
        a.corresponds(c)( (x:String,y:Int)=>x.equalsIgnoreCase(y.toString) )
        println(a.corresponds(b)(_.equalsIgnoreCase(_))) //true


        //控制抽象 control abstractions
        //eg.: 定义一个方法, 该方法以业务逻辑的代码块作为参数, 启动一个线程, 在线程中执行业务逻辑
        def runThread(block:()=>Unit)={
            new Thread{
                override def run(){
                    block()
                }
            }.start()
        }
        //调用
        runThread{ ()=>println("hhhhh");Thread.sleep(1000);println("beybye...") }

        //更为精简的写法, 注意block冒号后面的空格不能省略
        def runThread2(block: =>Unit)={
            new Thread{
                override def run(){ block }
            }.start()
        }
        //调用
        runThread2{ println("hhhhh");Thread.sleep(1000);println("beybye...") }

        def until(condition: =>Boolean)(block: =>Unit) {
            if(condition){
                block
                until(condition)(block)
            }
        }

        var x=10
        until(x == 10){
            x-=1
            println(x)
        }
    }

}
