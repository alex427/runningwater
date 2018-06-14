package alex.learn.toolrunner;

/**
 * Created by zhiguangai on 2017/9/7.
 */
public class MyJob implements  Tool {
    public static void main(String[] args){
        MyJob tool = new MyJob();
        ToolRunner.run(tool,args);
    }

    public int run(String[] args){
        System.out.println("I am working");
        return 1;
    }
}
