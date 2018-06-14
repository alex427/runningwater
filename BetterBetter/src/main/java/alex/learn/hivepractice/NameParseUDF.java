package alex.learn.hivepractice;


import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by zhiguangai on 2017/11/21.
 */
public class NameParseUDF extends UDF{
    public NameParseUDF(){

    };

    public String evaluate(String name){
        if(name.contains("andy")){
            return "correctname";
        }
        return "wrongname";
    }
}
