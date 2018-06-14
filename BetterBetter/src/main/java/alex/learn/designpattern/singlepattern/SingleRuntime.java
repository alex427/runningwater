package alex.learn.designpattern.singlepattern;

/**
 * zhiguang
 */
public class SingleRuntime {

    //最简单的单例模式, 提前初始化的方式

    //私有构造方法
    private SingleRuntime(){

    }

    //持有本类对象, 私有
    private  static final SingleRuntime runtime = new SingleRuntime();


    //提供对外的公共访问方法
    public static SingleRuntime getInstance(){
        return runtime;
    }

}
