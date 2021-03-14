package java;

import DataStorage.TextDataStorage;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TextDataStorageTest {

    @Test
    void getText() {
        TextDataStorage myText = new TextDataStorage(200, 200, "Hello World", "Arial", 10, Color.BLACK, 10);
        assertEquals("Hello World", myText.getText());
    }
}