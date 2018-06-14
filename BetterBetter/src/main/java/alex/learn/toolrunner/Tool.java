package alex.learn.toolrunner;

/**
 * Created by zhiguangai on 2017/9/7.
 * hadoop中的ToolRunner, 不知道它这样实现的好处是什么?
 * 看上去是ToolRunner.run, 在执行, 其实还是MyJob自己在执行自己的run方法.
 * 好处就是hadoop框架在你run的时候, 在背后添加了自己的东西, 比如说检查等, 特别是那几个注解@Public, @Stable
 */
public interface Tool {

    int run(String[] args);
}
