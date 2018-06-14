package alex.learn.wc;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class WcReducer extends Reducer{

    @Override
    public void reduce(Object key, Iterable values, Context context) throws IOException, InterruptedException {
        Integer counter = 0;
        Iterator it = values.iterator();
        while(it.hasNext()){
           counter++;
        }
        context.write(new Text(key.toString()),new LongWritable(counter));
    }
}
