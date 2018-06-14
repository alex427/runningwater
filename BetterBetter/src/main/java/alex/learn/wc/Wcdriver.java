package alex.learn.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Wcdriver {
    //alex.learn.wc.Wcdriver
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = new Job(conf,"myjob");

        job.setJarByClass(Wcdriver.class);
        job.setMapperClass(alex.learn.wc.WcMapper.class);
        job.setReducerClass(alex.learn.wc.WcReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        System.out.println("args[1]:  "+args[1]);
        System.out.println("args[2]:  "+args[2]);

        FileInputFormat.setInputPaths(job, args[1]);
        FileOutputFormat.setOutputPath(job, new Path( args[2]));

        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }
}
