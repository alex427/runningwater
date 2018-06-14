package alex.chapter10.exercise

import java.io.{FileInputStream, InputStream}


//第十题
class MyInterableInputStream(val fis : FileInputStream) extends InputStream with Iterable[Byte]{
    override def read(): Int = {
        this.fis.read()
    }

    override def iterator(): Iterator[Byte] = {
        val it = new MyIterator
        it
    }
}
