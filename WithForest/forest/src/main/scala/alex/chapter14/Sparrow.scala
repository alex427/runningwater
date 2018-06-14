package alex.chapter14


import alex.day0323.Animal


//定义样例类
abstract class Amount
case class Dollar(x:Double) extends Amount
case class Currency(x:Double,y:String) extends Amount
case object MyAmount extends Amount


sealed abstract class Story
case class ChineseStory(x:String) extends Story
case class BritishStory(x:String) extends Story
case class FrenchStory(x:String) extends Story


object Sparrow {

    def main(args: Array[String]): Unit = {
        val abc = "abc"
        val aba = abc match {
            case "" => println("null")
            case "abc" => "abc is my value"
            case _ => println("you are lost .. ")
        }
        println(aba)

        //采用竖线实现多种选择
        val prefix = "0aaa"
        val abb = prefix match {
            case "0x" | "0xx" => "binary"
            case "0a" | "0aa" => "unary"
            case _ => "Nothing"
        }
        println(abb)

        //采用枚举的方式
        val color = Color.Black
        val abd = color match {
            case Color.Red => "good"
            case Color.Yellow => "normal"
            case Color.Black => "bad"
        }
        println(abd)

        //守卫
        //case 后面跟上一个返回值为boolean的表达式, 这就是守卫
        //如果case匹配后, 跟着一个变量, 那么返回值将赋给这个变量, 比如下面的digit
        val ch = 'a'
        var sign = 0
        var dd = 0
        ch match {
            case _ if Character.isDigit(ch) => dd = Character.digit(ch, 10)
            case '+' => sign = 1
            case '-' => sign = -1
            case _ => sign = 0
        }


        //类型匹配
        //scala模式匹配可直接进行类型匹配, 而不需要isInstanceOf[T]进行判断
        val bird = new Bird(2)
        bird match {
            case a: Animal => println("I am a bird")
            case a: Flyable => println("I can fly") //如果第一个会匹配上, 所以这里会报错 unreadhable code
            case _ => println("not me .. ")
        }
        //模式匹配的返回值, 可以是一个函数
        val func = bird match {
            case a: Bird => (x: Int, y: Int) => {
                x * y
            }
            case _ => (x: Int, y: Int) => {
                x + y
            }
        }

        val res = func(10, 20)
        println(res)

        //可不可以加泛型
        val mm = Map("jeremy" -> 20, "jason" -> 30, "james" -> 40)
        mm match {
            //似乎下面两种写法都行, 都能正常运行.
            //case a:Map[String,Int] => println("with generic type")
            case a: Map[_, _] => println("not with generic type")
            case _ => println("error is found .. ")
        }

        //了解一下: 类型擦除
        //下面的class比较, 答案是true. 为什么? 因为类型擦除. eq判断发生在运行期, 而泛型起作用是在编译期, 过了编译期, 就会执行类型擦除, 没有泛型了.
        //泛型: 一种参数化的类型, 它允许开发者可以向传参数一样, 把类型传递过来, 他把运行期的风险, 转移到了编译期.
        //注意: 数组可不是这样的啊
        import java.util
        val l1 = new util.ArrayList[String]
        val l2 = new util.ArrayList[Integer]
        System.out.println(l1.getClass eq l2.getClass) //true

        val aar1 = Array[Int](3, 2)
        val aar2 = Array[String]("", "aa")
        println(aar1.getClass eq aar2.getClass) //false


        //数组, list和元组的模式匹配
        //没觉得这有什么特别之处
        //这里有个析构的概念, destructuring. 下面的x, 会在匹配上之后, 获得对应的值3,
        //这就有点意思了. 很明显程序能够让一个元素(pair)的部分(3)被访问到. 变量被直接绑定到一个元素的某个部分, 这就是析构.
        val pair = Tuple2(2, 3)
        pair match {
            case (0, x) => println("aaa")
            case (2, x) => println("bbb"+"  "+x)
            case _ => println("ccc")
        }

        //提取器
        //提取器就是方法: unapply, unapplySeq
        //第二种写法很有意思, 要掌握.
        val arr2 = Array(2,3,4,5)
        arr2 match {
            case Array(0,x)=> println("dddd")
            case Array(x, rest@_*) => println(rest.toBuffer)  //ArrayBuffer(3, 4, 5)
            case _ => println("eeeee")
        }


        //提取器特别适合正则表达式
        //注意: 当时用正则表达式提取器, 这个提取器可不再是一个伴生对象, 而是一个正则表达式对象
        val regx_str = "([0-9]+) ([a-z]+)".r
        val str = "99 bottles"
        str match {
            case regx_str(num, item) => println(num+"  "+item)
            case _ => println("not ok .. ")
        }

        //变量声明中的模式
        //声明x=2, y=3
        val (x,y) = (2,3)
        //声明first=2, second=3, 其余照旧
        val arr3 = Array(2,3,4,5)
        val Array(first,second, rest@_*) = arr3
        println(first +"   "+second)


        //样例类和样例对象
        //他们会自动生成apply方法和unapply方法
        val mydollar:Dollar = Dollar(100)
        mydollar match {
            case Dollar(x) => println("it is dollar  "+ x)
            //case Currency(5,"dd") => println("it is currency")
            case _ => println("xxx")
        }

        //密封类
        //没有搞懂
        val sotry:ChineseStory = ChineseStory("china")
        sotry match {
            case ChineseStory(y)=> println(y)
            //case BritishStory(x)=> println(x)
            //case FrenchStory(z)=> println(z)
            case _ => println("nnn")
        }

        //偏函数
        //第一个泛型是参数类型, 第二个是返回值类型
        //偏函数的语法不同于正常函数, 定义方式不同, 使用方式也不同
        //hive sql中的case when逻辑就可以用这个偏函数封装起来, 然后当做参数传入.
        val  partialfunc1: PartialFunction[Int,String] = {
            case 1 => "a"
            case 2 => "b"
            case _ => "nnnnn"
        }
        val res11 = partialfunc1(1)
        println(res11)

        val  partialfunc2: PartialFunction[Int,String] = {
            case 1 => "a"
            case 2 => "b"
            case _ => "nnnnn"
        }
        val res12 = partialfunc2(1)
        println(res12)
    }



}
