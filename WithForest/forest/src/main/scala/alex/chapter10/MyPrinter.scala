package alex.chapter10



class MyPrinter (val price:Double=1000.0, val brand:String="SX") extends SxPrinter with GoodLogger {

    def this(){
        this(1000.0,"SX")
        println("MyPrinter connstructor is working ")
    }

    override def savelog(path: String): Unit = {
        println("the implemented .. ")
    }
}
