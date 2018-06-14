package alex.chapter10.exercise

class MyRectangle extends RectangleLike {
    override var a: MyPoint = _
    override var b: MyPoint = _
    override var c: MyPoint = _
    override var d: MyPoint = _

    def this(a: MyPoint,b: MyPoint,c: MyPoint,d: MyPoint) {
        this
        this.a=a
        this.b=b
        this.c=c
        this.d=d
    }

}

object MyRectangle {
    def main(args: Array[String]): Unit = {
        val a = new MyPoint(2,1)
        val b = new MyPoint(12,1)
        val c = new MyPoint(2,10)
        val d = new MyPoint(12,10)
        val rectangle = new MyRectangle(a,b,c,d)
        rectangle.trnaslate(5,5)
        rectangle.grow(3,3)
    }
}