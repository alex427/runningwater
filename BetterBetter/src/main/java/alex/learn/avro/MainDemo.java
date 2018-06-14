package alex.learn.avro;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import alex.learn.avrodomain.AgPerson;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class MainDemo {
    //代码没走通, 奇怪
    public static void main(String[] args) throws IOException {
        //准备数据
        AgPerson person1 = new AgPerson("00001","jack",15,22,"black");

        AgPerson person2 = AgPerson.newBuilder()
                .setId("")
                .setPname("")
                .setFavnumber(8)
                .setFavcolor("red")
                .build();

        AgPerson person3 = AgPerson.newBuilder()
                .setId("")
                .setPname("")
                .setFavnumber(8)
                .setFavcolor("red")
                .build();

        //序列化到磁盘
        //String path = MainDemo.class.getClassLoader().getResource("person.avsc").getPath();
        DatumWriter<AgPerson> userDatumWriter = new SpecificDatumWriter<AgPerson>(AgPerson.class);
        DataFileWriter<AgPerson> dataFileWriter = new DataFileWriter<AgPerson>(userDatumWriter);

        dataFileWriter.create(person1.getSchema(), new File("/person.avro"));
        dataFileWriter.append(person1);
        dataFileWriter.append(person2);
        dataFileWriter.append(person3);
        dataFileWriter.close();

    }
}
