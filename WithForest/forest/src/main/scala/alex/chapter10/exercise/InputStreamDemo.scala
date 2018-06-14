package alex.chapter10.exercise

import java.io.{BufferedInputStream, File, FileInputStream, InputStream}

object InputStreamDemo {

    def main(args: Array[String]): Unit = {
        //val bis = new BufferedInputStream(new FileInputStream("path"))
        val bis = new MyBufferInputStream(new MyFileInputStream("path"), "wrapping")
        val aa = bis.read()
        println(aa)
    }

}
