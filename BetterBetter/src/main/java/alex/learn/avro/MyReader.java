package alex.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyReader {

    public void read(ByteArrayOutputStream bos) throws IOException {
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(getClass().getResourceAsStream("/person.avsc"));
        DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);

        Decoder decoder = DecoderFactory.get().binaryDecoder(bos.toByteArray(),null);
        GenericRecord record = reader.read(null,decoder);

        record.get("id");
        record.get("pname"); //输出类型是UTF8

        System.out.println(record.toString());
    }
}
