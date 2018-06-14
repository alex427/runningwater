package alex.chapter10.exercise

 class MyFileInputStream(val path:String) extends MyInputStream {
     override def read(): Int = {
         3
     }
 }
