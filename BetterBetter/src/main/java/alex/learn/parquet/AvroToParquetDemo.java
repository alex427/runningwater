package alex.learn.parquet;


import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import parquet.avro.AvroParquetWriter;

import java.io.IOException;

/*
* avro--parquet转换
* AvroParquetWriter这是程序提供的avro-parquet之间IO转换工具, 不过通常情况下, 最好还是解析数据, 封装到parquet的group中, 再写出
* */
public class AvroToParquetDemo {

    public static void main(String [] args) throws IOException {
        //1. 准备avro数据和avro schema
        Schema schema = new Schema.Parser().parse(AvroToParquetDemo.class.getClassLoader().getResourceAsStream("user.avsc"));
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

        GenericRecord user4 = new GenericData.Record(schema);
        user4.put("id", "10005");
        user4.put("pname", "mack");
        user4.put("age", 28);
        user4.put("favcolor", "brown");


        //2. 准备parquet目标文件和IO工具
        Path path = new Path("data.parquet");
        //AvroParquetWriter这是程序提供的avro-parquet之间IO转换工具, 不过通常情况下, 最好还是解析数据, 封装到parquet的group中, 再写出.
        AvroParquetWriter<GenericRecord> writer = new AvroParquetWriter<>(path,schema);

        writer.write(user1);
        writer.write(user2);
        writer.write(user3);
        writer.write(user4);

        writer.close();

    }
}
