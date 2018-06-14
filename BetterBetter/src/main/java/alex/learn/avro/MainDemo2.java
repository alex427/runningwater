package alex.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class MainDemo2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Schema schema = new Schema.Parser().parse(MainDemo.class.getClassLoader().getResourceAsStream("user.avsc"));

        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("id", "10002");
        user1.put("pname", "Alyssa");
        user1.put("age", 20);
        user1.put("favnumber", 256);

        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("id", "10003");
        user2.put("pname", "Ben");
        user2.put("age", 22);
        user2.put("favnumber", 7);
        user2.put("favcolor", "red");

        GenericRecord user3 = new GenericData.Record(schema);
        user3.put("id", "10004");
        user3.put("pname", "jack");
        user3.put("age", 18);
        user3.put("favnumber", 77);
        user3.put("favcolor", "balck");
        //favnumber 是union类型
        GenericRecord user4 = new GenericData.Record(schema);
        user4.put("id", "10005");
        user4.put("pname", "mack");
        user4.put("age", 28);
        user4.put("favcolor", "brown");
        //default的用法没有掌握
        GenericRecord user5 = new GenericData.Record(schema);
        user5.put("id", "10005");
        user5.put("age", 28);
        user5.put("favcolor", "brown");


        //将三个user序列化到磁盘文件中
        File file = new File("users.avro");
        //DatumWriter将Java对象转换成内存中的序列化格式. 他需要把schema和java对象进行比对,当然这个是在运行期进行的
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        //DataFileWriter这才是IO工具, 它的append方法其实调用IO流的write方法, 执行写入  this.dout.write(datum, this.bufOut);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        //dataFileWriter.append(user3); 会追加进去, 有两个user3
        dataFileWriter.append(user4);
        //dataFileWriter.append(user5);
        dataFileWriter.close();

        Thread.sleep(2000);

        //反序列化
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from allocating and garbage collecting many objects for files with
            // many items.  public D next(D reuse) {.....}
            user = dataFileReader.next(user);
            System.out.println(user);
            //{"id": "10002", "pname": "Alyssa", "age": 20, "favnumber": 256, "favcolor": null}
            //{"id": "10003", "pname": "Ben", "age": 22, "favnumber": 7, "favcolor": "red"}
            //{"id": "10004", "pname": "jack", "age": 18, "favnumber": 77, "favcolor": "balck"}
        }
    }
}