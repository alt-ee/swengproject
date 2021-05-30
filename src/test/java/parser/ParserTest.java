package parser;

import datastorage.Slideshow;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    void testValidFile() throws Exception {

        File xmlFile = new File("src/test/resources/PWS_example.xml");

        assertEquals(Slideshow.class, Parser.parse(xmlFile).getClass());
    }

    @Test
    void testInvalidFile() {

        File xmlFile = new File("src/test/resources/this_file_does_not_exist.xml");

        assertThrows(IOException.class, () -> Parser.parse(xmlFile));
    }


}
