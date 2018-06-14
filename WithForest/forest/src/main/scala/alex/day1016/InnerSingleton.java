package alex.day1016;

/**
 * Created by zhiguang on 2017/10/16.
 * 采用静态内部类来实现单例模式
 * 这是多线程环境下最简单的方式
 */
public class InnerSingleton {
    private static class Singleton{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstace(){
        return Singleton.singleton;
    }
}
