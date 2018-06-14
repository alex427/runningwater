package alex.day0323

/**
 * Created by zhiguang on 2018/4/7.
 */
class Sparrow extends Animal with Flyable{

    override def fly(wings: Int): String = {
        var height = ""+ wings
        height
    }
}
