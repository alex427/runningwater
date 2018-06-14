package alex.chapter14

class Bird(val wings:Int) extends Flyable {
    override def fly(): Unit = {
        println("I can fly .. ")
    }
}
