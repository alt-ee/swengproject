package datastorage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Slideshow {
    HashMap<String, SlideDataStorage> slides;

    SlideDataStorage currentSlide;

    Color defaultBackgroundColour;
    String defaultFont;
    int defaultFontSize;
    Color defaultTextColour;
    Color defaultLineColour;
    Color defaultShapeColour;

    public Slideshow() {
        slides = new HashMap<String, SlideDataStorage>();
    }

    public void setDefaults (Color background, String font, int fontSize, Color textColour, Color lineColour, Color shapeColour) {
        defaultBackgroundColour = background;
        defaultFont = font;
        defaultFontSize = fontSize;
        defaultTextColour = textColour;
        defaultLineColour = lineColour;
        defaultShapeColour = shapeColour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slideshow slideshow = (Slideshow) o;
        return defaultFontSize == slideshow.defaultFontSize &&
                slides.equals(slideshow.slides) &&
                defaultBackgroundColour.equals(slideshow.defaultBackgroundColour) &&
                defaultFont.equals(slideshow.defaultFont) &&
                defaultTextColour.equals(slideshow.defaultTextColour) &&
                defaultLineColour.equals(slideshow.defaultLineColour) &&
                defaultShapeColour.equals(slideshow.defaultShapeColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slides, defaultBackgroundColour, defaultFont, defaultFontSize, defaultTextColour, defaultLineColour, defaultShapeColour);
    }

    public void addSlide(SlideDataStorage slide) {
        String id = slide.getId();
        slides.put(id, slide);
    }
    public void setCurrentSlide(String id) {
        if (slides.containsKey(id)) {
            currentSlide = slides.get(id);
        }
    }

    public SlideDataStorage getCurrentSlide() {
        return currentSlide;
    }

    public Color getDefaultBackgroundColour() {
        return defaultBackgroundColour;
    }

    public String getDefaultFont() {
        return defaultFont;
    }

    public int getDefaultFontSize() {
        return defaultFontSize;
    }

    public Color getDefaultTextColour() {
        return defaultTextColour;
    }

    public Color getDefaultLineColour() {
        return defaultLineColour;
    }

    public Color getDefaultShapeColour() {
        return defaultShapeColour;
    }
}
