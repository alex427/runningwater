package alex.chapter10



//Logger with Cloneable with Serializable 是作为一个整体来被继承的
class ConsoleLogger extends  Logger with Cloneable with Serializable {
    override def log(msg: String): Unit = {println( msg+ " console logger is working")}

    def log2(msg: Double): Unit = {println( msg+ " console logger is working .. ")}
}
