package alex.learn.wc;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WcMapper extends Mapper{

    @Override
    public void map(Object key, Object value, Context context) throws IOException, InterruptedException {
        //读取一行
        String[] arr = value.toString().split(" ");
        for (int i = 0; i < arr.length; i++) {
            context.write(new Text(arr[i]),new LongWritable(1));
        }
    }
}
