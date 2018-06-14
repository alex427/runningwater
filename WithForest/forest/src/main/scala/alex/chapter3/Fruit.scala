package alex.chapter3

object Fruit {

    def main(args: Array[String]): Unit = {
        val b = Array(1,2,3,34,52,12,23,42)
        val a = b.toBuffer
        val pos =for(i <- a.indices  if a(i)>=0 ) yield i
        for(j <- pos.indices ) {
            a(j) = a(pos(j))
            a.trimEnd(a.length-pos.length)
        }
        println(a)

    }

}
