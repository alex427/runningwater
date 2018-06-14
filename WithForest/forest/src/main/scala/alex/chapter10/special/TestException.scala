package alex.chapter10.special

class TestException extends TraitException {
    override def abmethod(): Unit = {
        println("it is implemented .. ")
    }
}
object TestException{
    def main(args: Array[String]): Unit = {
        val aa = new TestException
        aa.work()
        aa.destroy()
    }
}