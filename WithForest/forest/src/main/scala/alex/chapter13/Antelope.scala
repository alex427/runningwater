package alex.chapter13

class Antelope(val horns: Int) {

    println("the construct statements")

    def apply(horns:Int): Antelope ={
        println("I am applying in class.. ")
        val lope = new Antelope(horns)
        lope
    }

}


object Antelope{

    println("I am the object statements .. ")

    def apply(horns:Int): Antelope ={
        println("I am applying in object .. ")
        val lope = new Antelope(horns)
        lope
    }
}