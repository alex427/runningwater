package alex.learn.designpattern.singlepattern;

/**
 * zhiguang
 */
public class ParalleSingleRuntime {

    //单例模式2: double check + volatile
    //以前没有用volatile, 只是用static, 但是今天发现, 如果并发度非常高, 会出现奇怪问题
    //但是加了volatile, 就不会, 原因还没找到

    //持有本类对象, 私有化
    //采用lazy的方式
    private static volatile ParalleSingleRuntime runtime ;

    //私有化构造方法
    private ParalleSingleRuntime(){

    }

    //这种写法在并发情况下, 并不能绝对保证到全局单例
    public static ParalleSingleRuntime getInstance(){
        if(runtime == null){
            runtime = new ParalleSingleRuntime();
            return runtime;
        }
        return runtime;
    }

    //并发下, 单例
    //通过在getInstance方法上加同步锁来实现
    //但是同步锁会带来性能下降,客观上降低了并发性,所以还是别用这个, 虽然好理解
    public static synchronized ParalleSingleRuntime getInstance_a(){
        if(runtime == null){
            runtime = new ParalleSingleRuntime();
            return runtime;
        }
        return runtime;
    }

    //并发下, 单例
    //通过double check 的方式来实现
    //double check + volatile是一种比较可靠的做法
    public static ParalleSingleRuntime getInstance_b(){
        if(runtime == null){
            synchronized (ParalleSingleRuntime.class){
                if (runtime == null){
                    runtime = new ParalleSingleRuntime();
                    return runtime;
                }
            }
        }
        return runtime;
    }

}
