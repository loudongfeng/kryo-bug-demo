import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.apache.hadoop.hive.serde2.columnar.ColumnarStruct;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.io.File.createTempFile;

public class KryoTest {

    @Test
    public void testSerialize() throws Exception {
        ColumnarStruct row = new ColumnarStruct(10);

        try {
            final File tempFile = createTempFile("test", ".dat");
            final Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            ((DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
            kryo.setReferences(false);
            Output output = new Output(new FileOutputStream(tempFile));
            kryo.writeObject(output, row);
            output.close();

            final Input input = new Input(new FileInputStream(tempFile));
            ColumnarStruct row1 = kryo.readObject(input, ColumnarStruct.class);
            row1.randomPrint();
        } catch (FileNotFoundException e) {
            throw new IOException("Error with Kryo IO", e);
        }
    }
}
