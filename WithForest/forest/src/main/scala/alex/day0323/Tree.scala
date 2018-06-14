package alex.day0323

class Tree private (var color:String, var height:Int){

}

object Tree{

    def apply( color: String,  height: Int): Tree = new Tree(color, height)

    def main(args: Array[String]): Unit = {
        val tree = Tree("black",232) //直接调用伴生对象中的apply方法

        //包引入时, 进行重命名
        import java.util.{HashMap => JavaHashMap}
        var jhm = new JavaHashMap[String,Int]()
        jhm.put("aa",1231)
        jhm.put("ab",1232)
        jhm.put("ac",1233)

        val jhm2 = new JavaHashMap[String,Int]()
        jhm2.put("ba",1234)
        jhm2.put("bb",1235)
        jhm2.put("bc",1236)

        println(jhm)
        println(jhm2)

        //scala 的不可变map, 没法添加元素
        val immute_map = Map( ("cortisol",2),("serotonin",3),("adreneline",5),("dophamin",25) )

        import scala.collection.mutable._
        val mutable_map = Map( ("aa",2),("dd",3),("cc",5),("bb",25) )
        mutable_map.put("ddd",33)


    }
}