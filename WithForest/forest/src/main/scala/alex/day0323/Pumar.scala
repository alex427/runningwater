package alex.day0323

object Pumar {

    def main(args: Array[String]):Unit={
        //1. 通过主构造器构造对象
        //执行顺序: class body中的statement, 构造器
        val plant = new Plant("rose","earth",func)
        println(plant.colour+" "+plant.home+"  "+plant.name)
        println("...................................")

        //2. 通过辅助构造器构造对象
        //执行顺序: class body中的statement, 构造器
        val plant2 = new Plant("red","rose","earth",func)
        println(plant2.colour+" "+plant2.home+"  "+plant2.name)



    }


    val func = (x:Int)=> {
        println("main constructor is working now ...")
        x
    }

}
