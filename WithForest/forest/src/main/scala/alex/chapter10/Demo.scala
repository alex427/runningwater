package alex.chapter10

object Demo {

    def main(args: Array[String]): Unit = {

        val mp = new MyPrinter()
        mp.printsx("is it printed ??")



        //居然还能这样
        val myPrinter = new FjPrinter(2000.0,"xxx") with GoodLogger {
            override def savelog(path: String): Unit = {  //这是trait中的抽象方法, 必须实现, 另外它在对象构造时并不调用
                println(path + "还能这样操作啊...")
            }
        }
        myPrinter.log(78000)



    }

}
