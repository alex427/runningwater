package alex.learn.designpattern.singlepattern;

/**
 * zhiguang
 */
public class StaticHolderRuntime {
    //第三种方式: 静态内部类+延迟初始化
    //用一个静态的内部类做为holder, 持有全局单例
   private static class SingletonHolder {
        private static StaticHolderRuntime runtime = new StaticHolderRuntime();
    }

    private StaticHolderRuntime(){

    }

    public static StaticHolderRuntime getInstance(){
       return SingletonHolder.runtime;
    }
}
