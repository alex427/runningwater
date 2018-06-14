package alex.chapter13

class Chapter13Demo {

}

object Chapter13Demo {
    def main(args: Array[String]): Unit = {

        val list = List(1,22,3,12,24)
        val iterator = list.iterator
        while(iterator.hasNext){
            val ele = iterator.next()
            println(ele)
        }

    }

}
