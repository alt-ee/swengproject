package parser;

import datastorage.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SlideshowHandler extends DefaultHandler {

    private Color defaultBackgroundColour;
    private String defaultFont;
    private int defaultFontSize;
    private Color defaultFontColour;
    private Color defaultLineColour;
    private Color defaultShapeColour;

    private Slideshow slideshow;
    private String firstSlideID;

    private String slideshowPath;

    private SlideDataStorage tempSlide;
    private TextDataStorage tempText;
    private ShapeDataStorage tempShape;
    private ShaderDataStorage tempShader;
    private ButtonDataStorage tempButton;
    private HashMap<String, Integer> buttonVars;

    private String elementValue;

    public SlideshowHandler(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

    @Override
    public void characters(char [] chars, int start, int length) {
        elementValue = new String(chars, start, length);
    }

    @Override
    public void startDocument() {
        slideshow = new Slideshow();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName.toLowerCase()) {
            case "defaults": {
                defaultBackgroundColour = Color.decode(attributes.getValue("backgroundcolour"));
                defaultFont = attributes.getValue("font");
                defaultFontSize = Integer.parseInt(attributes.getValue("fontsize"));
                defaultFontColour = Color.decode(attributes.getValue("fontcolour"));
                defaultLineColour = Color.decode(attributes.getValue("linecolour"));
                defaultShapeColour = Color.decode(attributes.getValue("fillcolour"));

                slideshow.setDefaults(defaultBackgroundColour, defaultFont, defaultFontSize, defaultFontColour, defaultLineColour, defaultShapeColour);

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
                if (buttonVars == null) {
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
                } else {
                    tempButton = new TextButton(
                            buttonVars.get("xPos"),
                            buttonVars.get("yPos"),
                            buttonVars.get("width"),
                            buttonVars.get("height"),
                            "", // This is hacky but i don't have time to think of a better solution
                            attributes.getValue("font"),
                            Integer.parseInt(attributes.getValue("fontsize")),
                            Color.decode(attributes.getValue("fontcolour"))
                    );
                }

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
                URL location = null;
                try {
                    location = new URL("file:///" + slideshowPath + attributes.getValue("urlname"));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if (buttonVars == null) {
                    ImageDataStorage tempImage;

                    int xPos = Integer.parseInt(attributes.getValue("xstart"));
                    int yPos = Integer.parseInt(attributes.getValue("ystart"));
                    int width = Integer.parseInt(attributes.getValue("width"));
                    int height = Integer.parseInt(attributes.getValue("height"));

                    int duration;
                    String durationString = attributes.getValue("duration");
                    if (durationString != null) {
                        duration = Integer.parseInt(durationString);
                    } else {
                        duration = 0;
                    }

                    tempImage = new ImageDataStorage(xPos, yPos, location, width, height, duration);

                    tempSlide.addImage(tempImage);
                } else {
                    tempButton = new ImageButton(
                            buttonVars.get("xPos"),
                            buttonVars.get("yPos"),
                            buttonVars.get("width"),
                            buttonVars.get("height"),
                            location
                    );
                }
                break;
            }
            case "audio": {
                AudioDataStorage tempAudio;

                URL location = null;
                try {
                    location = new URL("file:///" + slideshowPath + attributes.getValue("urlname"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                boolean loop = Boolean.parseBoolean(attributes.getValue("loop"));

                String startTimeString = attributes.getValue("starttime");
                String idString = attributes.getValue("id");
                if (startTimeString != null) {
                    int startTime = Integer.parseInt(startTimeString);
                    tempAudio = new AudioDataStorage(location, startTime, loop);
                } else if (idString != null) {
                    String id = attributes.getValue("id");

                    tempAudio = new AudioDataStorage(location, id, loop);
                } else {
                    tempAudio = new AudioDataStorage(location, 0, loop);
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
                    location = new URL("file:///" + slideshowPath + attributes.getValue("urlname"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                boolean loop = Boolean.parseBoolean(attributes.getValue("loop"));

                String startTimeString = attributes.getValue("starttime");
                String idString = attributes.getValue("id");
                if (startTimeString != null) {
                    int startTime = Integer.parseInt(startTimeString);
                    tempVideo = new VideoDataStorage(xPos, yPos, location, startTime, loop);
                } else if (idString != null) {
                    tempVideo = new VideoDataStorage(xPos, yPos, location, idString, loop);
                } else {
                    tempVideo = new VideoDataStorage(xPos, yPos, location, 0, loop);
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
            case "shading": {

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

            case "button": {
                buttonVars = new HashMap<>();
                buttonVars.put("xPos", Integer.parseInt(attributes.getValue("xstart")));
                buttonVars.put("yPos", Integer.parseInt(attributes.getValue("ystart")));
                buttonVars.put("width", Integer.parseInt(attributes.getValue("width")));
                buttonVars.put("height", Integer.parseInt(attributes.getValue("height")));

                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName.toLowerCase()) {
            case "slide":
                slideshow.addSlide(tempSlide);
                if (firstSlideID == null) {
                    firstSlideID = tempSlide.getId();
                    slideshow.setCurrentSlide(firstSlideID);
                }

                break;

            case "text":
                if (buttonVars == null) {
                    tempText.appendText(elementValue);
                    tempText.appendText("</html>");
                    tempSlide.addText(tempText);
                } else {
                    ((TextButton)tempButton).setText(elementValue); // Gross
                }

                break;

            case "b":
                tempText.appendBoldText(elementValue);

                break;

            case "i":
                tempText.appendItalicText(elementValue);
                break;

            case "shape":
                tempSlide.addShape(tempShape);
                break;

            case "button":
                tempSlide.addButton(tempButton);
                buttonVars = null;
                break;

            case "slideid": {
                tempButton.setTarget(ButtonDataStorage.Target.slide);
                tempButton.setId(elementValue);

                break;
            }

            case "mediaid": {
                tempButton.setTarget(ButtonDataStorage.Target.media);
                tempButton.setId(elementValue);

                break;
            }
        }
    }

    public Slideshow getSlideshow() {
        return slideshow;
    }
}
