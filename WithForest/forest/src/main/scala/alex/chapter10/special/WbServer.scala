package alex.chapter10.special

class WbServer {

}


object WbServer {
    def main(args: Array[String]): Unit = {

        val wbServer = new WbServer with MyFileLogger {
            override val filename: String = "2016030201.log"
        }


    }
}