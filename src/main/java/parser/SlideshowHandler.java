package parser;

import DataStorage.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.sound.sampled.Line;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class SlideshowHandler extends DefaultHandler {

    private Color defaultBackgroundColour;
    private String defaultFont;
    private int defaultFontSize;
    private Color defaultFontColour;
    private Color defaultLineColour;
    private Color defaultShapeColour;

    private ArrayList<SlideDataStorage> slideshow;

    private SlideDataStorage tempSlide;
    private TextDataStorage tempText;
    private ShapeDataStorage tempShape;
    private ShaderDataStorage tempShader;

    private String elementValue;

    @Override
    public void characters(char [] chars, int start, int length) throws SAXException {
        elementValue = new String(chars, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        slideshow = new ArrayList<SlideDataStorage>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "defaults": {
                defaultBackgroundColour = Color.decode(attributes.getValue("backgroundcolour"));
                defaultFont = attributes.getValue("font");
                defaultFontSize = Integer.parseInt(attributes.getValue("fontsize"));
                defaultFontColour = Color.decode(attributes.getValue("fontcolour"));
                defaultLineColour = Color.decode(attributes.getValue("linecolour"));
                defaultShapeColour = Color.decode(attributes.getValue("fillcolour"));

                break;
            }
            case "slide": {
                String id = attributes.getValue("id");
                int duration;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    duration = Integer.parseInt(durationString);
                } else {
                    duration = 0;
                }

                tempSlide = new SlideDataStorage(id, duration);

                break;
            }
            case "text": {
                int xPos = Integer.parseInt(attributes.getValue("xstart"));
                int yPos = Integer.parseInt(attributes.getValue("ystart"));

                String font;
                String fontString = attributes.getValue("font");
                if (fontString != null) {
                    font = fontString;
                } else {
                    font = defaultFont;
                }

                int fontSize;
                String fontSizeString = attributes.getValue("fontsize");
                if (fontSizeString != null) {
                    fontSize = Integer.parseInt(fontSizeString);
                } else {
                    fontSize = defaultFontSize;
                }

                Color fontColour;
                String fontColourString = attributes.getValue("fontcolour");
                if (fontColourString != null) {
                    fontColour = Color.decode(fontColourString);
                } else {
                    fontColour = defaultFontColour;
                }

                int duration;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    duration = Integer.parseInt(durationString);
                } else {
                    duration = 0;
                }

                tempText = new TextDataStorage(xPos, yPos, "<html>", font, fontSize, fontColour, duration);

                break;
            }
            case "b":
            case "i": {
                // if a bold or italic element starts then we can assume that either standard text or nothing
                // proceeded it and append this to the text.
                tempText.appendText(elementValue);

                break;
            }
            case"image": {
                ImageDataStorage tempImage;

                int xPos = Integer.parseInt(attributes.getValue("xstart"));
                int yPos = Integer.parseInt(attributes.getValue("ystart"));
                int width = Integer.parseInt(attributes.getValue("width"));
                int height = Integer.parseInt(attributes.getValue("height"));
                URL location = null;
                try {
                    location = new URL("file://" + attributes.getValue("urlname"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                int duration;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    duration = Integer.parseInt(durationString);
                } else {
                    duration = 0;
                }

                tempImage = new ImageDataStorage(xPos, yPos, location, width, height, duration);

                tempSlide.addImage(tempImage);
                break;
            }
            case "audio": {
                AudioDataStorage tempAudio;

                URL location = null;
                try {
                    location = new URL("file://" + attributes.getValue("urlname"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                boolean loop = Boolean.parseBoolean(attributes.getValue("loop"));

                int startTime;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    startTime = Integer.parseInt(durationString);
                    tempAudio = new AudioDataStorage(location, startTime, loop);
                } else {
                    String id = attributes.getValue("id");

                    //TODO raise exception if neither starttime nor id are set

                    tempAudio = new AudioDataStorage(location, id, loop);
                }

                tempSlide.addAudio(tempAudio);

                break;
            }
            case "video": {
                VideoDataStorage tempVideo;

                int xPos = Integer.parseInt(attributes.getValue("xstart"));
                int yPos = Integer.parseInt(attributes.getValue("ystart"));

                URL location = null;
                try {
                    location = new URL("file://" + attributes.getValue("urlname"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                boolean loop = Boolean.parseBoolean(attributes.getValue("loop"));

                int startTime;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    startTime = Integer.parseInt(durationString);
                    tempVideo = new VideoDataStorage(xPos, yPos, location, startTime, loop);
                } else {
                    String id = attributes.getValue("id");

                    //TODO raise exception if neither starttime nor id are set

                    tempVideo = new VideoDataStorage(xPos, yPos, location, id, loop);
                }

                tempSlide.addVideo(tempVideo);
                break;
            }

            case "line": {
                LineDataStorage tempLine;

                int x1 = Integer.parseInt(attributes.getValue("xstart"));
                int y1 = Integer.parseInt(attributes.getValue("ystart"));
                int x2 = Integer.parseInt(attributes.getValue("xend"));
                int y2 = Integer.parseInt(attributes.getValue("yend"));

                Color lineColour;
                String lineColourString = attributes.getValue("linecolour");
                if (lineColourString != null) {
                    lineColour = Color.decode(lineColourString);
                } else {
                    lineColour = defaultLineColour;
                }

                int duration;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    duration = Integer.parseInt(durationString);
                } else {
                    duration = 0;
                }

                tempLine = new LineDataStorage(x1, y1, x2, y2, lineColour, duration);
                tempSlide.addLine(tempLine);

                break;
            }
            case "shape": {

                int xPos = Integer.parseInt(attributes.getValue("xstart"));
                int yPos = Integer.parseInt(attributes.getValue("ystart"));
                int width = Integer.parseInt(attributes.getValue("width"));
                int height = Integer.parseInt(attributes.getValue("height"));

                Color shapeColour;
                String shapeColourString = attributes.getValue("fillcolour");
                if (shapeColourString != null) {
                    shapeColour = Color.decode(shapeColourString);
                } else {
                    shapeColour = defaultShapeColour;
                }

                int duration;
                String durationString = attributes.getValue("duration");
                if (durationString != null) {
                    duration = Integer.parseInt(durationString);
                } else {
                    duration = 0;
                }

                switch (attributes.getValue("type").toLowerCase()) {
                    case "oval" :
                        tempShape = new ShapeDataStorage(xPos, yPos, width, height, shapeColour, duration, ShapeDataStorage.Shapes.Oval);
                        break;
                    case "rectangle":
                        tempShape = new ShapeDataStorage(xPos, yPos, width, height, shapeColour, duration, ShapeDataStorage.Shapes.Rectangle);
                        break;
                }

                break;
            }
            case "shading":

                int x1 = Integer.parseInt(attributes.getValue("x1"));
                int y1 = Integer.parseInt(attributes.getValue("y1"));
                int x2 = Integer.parseInt(attributes.getValue("x2"));
                int y2 = Integer.parseInt(attributes.getValue("y2"));

                Color colour1 = Color.decode(attributes.getValue("colour1"));
                Color colour2 = Color.decode(attributes.getValue("colour2"));

                boolean cyclic = Boolean.parseBoolean(attributes.getValue("cyclic"));

                tempShader = new ShaderDataStorage(x1, y1, x2, y2, colour1, colour2, cyclic);
                tempShape.setShader(tempShader);

                break;

        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "slide":
                slideshow.add(tempSlide);

                break;
            case "text":
                tempText.appendText(elementValue);
                tempText.appendText("</html>");
                tempSlide.addText(tempText);

                break;
            case "b":
                tempText.appendBoldText(elementValue);

                break;
            case "i":
                tempText.appendItalicText(elementValue);
                break;
        }
    }

    public ArrayList<SlideDataStorage> getSlideshow() {
        return slideshow;
    }

    //TODO getter for finished slideshow
}
