package alex.chapter10.exercise

import alex.chapter10.GoodLogger

class MyBufferInputStream(inputstream: MyInputStream,msg:String) extends MyFilterInputstream(inputstream) with GoodLogger{

    override def read(): Int = {

        println("I want decorate ... again ..")
        this.log(20000)
        this.inputstream.read()
    }

    override def savelog(path: String): Unit = {

    }
}
