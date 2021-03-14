package DataStorage;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TextDataStorageTest {


    @Test
    void appendText() {
        TextDataStorage myText = new TextDataStorage(200, 200, "", "Arial", 10, Color.BLACK, 10);
        myText.appendText("some normal text ");
        assertEquals("some normal text ", myText.getText());
    }

    @Test
    void appendBoldText() {
        TextDataStorage myText = new TextDataStorage(200, 200, "", "Arial", 10, Color.BLACK, 10);
        myText.appendBoldText("some bold text ");
        assertEquals("<b>some bold text </b>", myText.getText());
    }

    @Test
    void appendItalicText() {
        TextDataStorage myText = new TextDataStorage(200, 200, "", "Arial", 10, Color.BLACK, 10);
        myText.appendItalicText("some italic text ");
        assertEquals("<i>some italic text </i>", myText.getText());
        System.out.println(myText.getText());
    }

    @Test
    void appendAllText() {
        TextDataStorage myText = new TextDataStorage(200, 200, "", "Arial", 10, Color.BLACK, 10);
        myText.appendText("some normal text ");
        myText.appendItalicText("some italic text ");
        myText.appendBoldText("some bold text ");
        assertEquals("some normal text <i>some italic text </i><b>some bold text </b>", myText.getText());
        System.out.println(myText.getText());
    }
}