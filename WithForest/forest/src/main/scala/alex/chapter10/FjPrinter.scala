package alex.chapter10

class FjPrinter  (val price:Double=200.0, val brand:String="FJ") {

    def FjPrinter(): Unit ={
        println("FjPrinter constructor is working  ")
    }

    def this(testmsg: String) {
        this
        println(testmsg)
    }

}
