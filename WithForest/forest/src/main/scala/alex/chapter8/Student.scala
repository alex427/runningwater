package alex.chapter8

class Student (val id:Int){

    def readbook():String = {
        "programing ... "
    }
}


object Student {
    def main(args: Array[String]): Unit = {

        //匿名子类
        val anotherStudent = new Student(15){
            override def readbook(): String = {
                "soft .. "
            }
        }

        println(anotherStudent.getClass)

    }
}