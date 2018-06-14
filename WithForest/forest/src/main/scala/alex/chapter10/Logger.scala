package alex.chapter10

trait Logger {
    //抽象方法
    //无需采用abstract关键字, 没有实现的方法即是抽象方法
    def log(msg:String)
    //用上的话, 编译不报错, 运行时报错
    //错误提示: abstract modifier can be used only for classes
    //abstract def log2(msg:String)


}
