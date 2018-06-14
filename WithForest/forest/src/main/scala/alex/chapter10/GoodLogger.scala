package alex.chapter10

trait GoodLogger {

    println("trait constructor is working" )

    //val dd =   new MyPrinter(2000.0,"xxx")
    //println("trait constructor is working       "+dd.brand+"  printer  "+dd.price)


    def log(msg:Double)={ println( "Logging:  "+msg )}
    def info(msg:Double)={ println( "Info:  "+msg )}
    def warn(msg:Double)={ println( "Warning:  "+msg )}
    def fatal(msg:Double)={ println( "Fatal Error:  "+msg )}

    def savelog( path: String)


}
