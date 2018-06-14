package alex.learn.parquet;

import parquet.column.ParquetProperties;
import parquet.example.data.Group;
import parquet.example.data.GroupFactory;
import parquet.example.data.simple.SimpleGroupFactory;
import parquet.hadoop.ParquetReader;
import parquet.hadoop.ParquetWriter;
import org.apache.hadoop.fs.Path;
import parquet.hadoop.example.GroupReadSupport;
import parquet.hadoop.example.GroupWriteSupport;
import org.apache.hadoop.conf.Configuration;
import parquet.schema.MessageType;
import parquet.schema.MessageTypeParser;

import java.io.IOException;

/*
*  一个最简单的, 基于HDFS的parquet IO操作
* */
public class ParquetDemo {

    public static void main(String [] args) throws IOException {
        //1. 首先定义schema(message)
       /* message WeatherRecord {
            required int32 year;
            required int32 temperature;
            required binary stationId (UTF8);
        }*/
        MessageType schema = MessageTypeParser.parseMessageType(
                "message Person {\n" +
                "  required binary id (UTF8);\n" +
                "  required binary name (UTF8);\n" +
                "}");
        //2. 准备数据
        //Group是parquet数据模型的重要概念
        //writesupport是java的parquet支持组件
        GroupFactory factory = new SimpleGroupFactory(schema);
        Group group = factory.newGroup();
        group.append("id","10005").append("name","zuck");

        Configuration configuration = new Configuration();
        GroupWriteSupport writeSupport = new GroupWriteSupport();

        writeSupport.setSchema(schema,configuration);

        //3. 设置parquet
        //3.1 设置目标文件
        Path path = new Path("data.parquet");
        ParquetWriter<Group> parquetwriter = new ParquetWriter<Group>(path,writeSupport,
                ParquetWriter.DEFAULT_COMPRESSION_CODEC_NAME,
                ParquetWriter.DEFAULT_BLOCK_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE, /* dictionary page size */
                ParquetWriter.DEFAULT_IS_DICTIONARY_ENABLED,
                ParquetWriter.DEFAULT_IS_VALIDATING_ENABLED,
                ParquetProperties.WriterVersion.PARQUET_1_0,
                configuration
        );
        //这些参数的默认值如下:
        /*public static final int DEFAULT_BLOCK_SIZE = 134217728;   //block块默认128M
        public static final int DEFAULT_PAGE_SIZE = 1048576;   //page页默认1M
        public static final CompressionCodecName DEFAULT_COMPRESSION_CODEC_NAME;   //默认不压缩
        public static final boolean DEFAULT_IS_DICTIONARY_ENABLED = true;  //默认采用字典编码
        public static final boolean DEFAULT_IS_VALIDATING_ENABLED = false;*/

        //4. 序列化到磁盘文件
        parquetwriter.write(group);
        parquetwriter.close();

        //5. 反序列化,读取
        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = new ParquetReader<Group>(path,readSupport);

        Group result = reader.read();
        System.out.println(result);
    }

}
