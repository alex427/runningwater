package alex.chapter8

abstract class Person (val name:String){

    //method
    def greetg():String

}


object Person{
    def main(args: Array[String]): Unit = {
        //匿名子类
        val fred = new Person("Fred") {
             def walk(): String = {"Hello Fred ... "}

            override def greetg(): String = {"ddddd"}
        }

        println(fred .getClass)   // class    alex.chapter8.Person$$anon$1
    }
}