package alex.day0323

object Cat {

    def main(args: Array[String]): Unit = {

        val ran = BigInt.probablePrime(100, scala.util.Random)
        println(ran) //1208540352074250457119266246857
        //格式化输出
        //  %d  有符号十进制整数
        //  %s  字符串
        //  %n  换行s
        printf("Hello, %s is tough, he is %d years old. %n", "jack", 22)


       //遍历字符串
        var s = ""
        var s2 = 0
        for(c <- "two tigers on the way"){
            s += c
            s2 += c
        }
        println(s)
        println(s2)
    }

}
