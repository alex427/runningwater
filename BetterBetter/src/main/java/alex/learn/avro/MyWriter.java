package alex.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyWriter {

    public void wrrite() throws IOException {
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(getClass().getResourceAsStream("/person.avsc"));

        GenericRecord person = new GenericData.Record(schema);

        person.put("id", "1001");
        person.put("pname", "Jack");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);

        Encoder encoder = EncoderFactory.get().binaryEncoder(bos, null);
        writer.write(person, encoder);
        encoder.flush();
        bos.close();
    }

    public ByteArrayOutputStream wrrite2() throws IOException {
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(getClass().getResourceAsStream("/person.avsc"));

        GenericRecord person = new GenericData.Record(schema);

        person.put("id", "1001");
        person.put("pname", "Jack");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        return bos;
    }
}
