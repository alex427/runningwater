package alex.chapter10.exercise

trait RectangleLike {

    //矩形需要四个坐标来约束
    //抽象字段, subclass必须实现
    var a: MyPoint
    var b: MyPoint
    var c: MyPoint
    var d: MyPoint

    //concrete methods
    //平面移动
    def trnaslate(dx:Int, dy:Int):Unit = {
        a= new MyPoint(a.x-dx,a.y-dy)
        b= new MyPoint(b.x-dx,b.y-dy)
        c= new MyPoint(c.x-dx,c.y-dy)
        d= new MyPoint(d.x-dx,d.y-dy)
    }

    //增长
    def grow(h:Int, v:Int):Unit = {
        var hh = h/2
        var vv = v/2
        a= new MyPoint(a.x-hh,a.y-vv)
        b= new MyPoint(b.x+hh,b.y-vv)
        c= new MyPoint(c.x-hh,c.y+vv)
        d= new MyPoint(d.x+hh,d.y+vv)
    }
}
