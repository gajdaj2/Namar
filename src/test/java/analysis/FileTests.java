package analysis;

import org.apache.logging.log4j.core.util.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FileTests {


    @Test
    public void TestFile(){
        Loader load = new Loader();
        try {
            String loadFile = load.ReadFile();
            Assert.isNonEmpty(load.ReadFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
