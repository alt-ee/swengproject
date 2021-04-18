package parser;

import datastorage.Slideshow;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void testValidFile() throws Exception {

        File xmlFile = new File("src/test/resources/PWS_example.xml");

        assertEquals(Slideshow.class, Parser.parse(xmlFile).getClass());
    }

}
