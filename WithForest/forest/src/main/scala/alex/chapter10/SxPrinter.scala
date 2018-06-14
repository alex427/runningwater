package alex.chapter10

class SxPrinter(val aa:Int=10) {

    def this(){
        this(10)
        println("SxPrinter constructor is working  ")
    }

    def printsx(testmsg: String){
        println(testmsg)
    }

}
