package alex.chapter10.exercise

class MyFilterInputstream(val inputstream: MyInputStream)  extends MyInputStream {

    override def read(): Int = {

        println("I want decorate ..")
        this.inputstream.read()
    }
}
