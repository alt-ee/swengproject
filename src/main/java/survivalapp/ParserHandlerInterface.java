package survivalapp;

import audiohandler.AudioPlayer;
import datastorage.*;
import graphicshandler.GraphicsPanel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ParserHandlerInterface
{
    public void toHandlers(Slideshow slideshow) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        ArrayList<SlideDataStorage> slideshowArr = slideshow.getSlides();

        AudioDataStorage audio;
        ShapeDataStorage shape;
        LineDataStorage line = null;

        ArrayList<AudioDataStorage> audioArr = new ArrayList<>();
        ArrayList<ShapeDataStorage> shapeArr = new ArrayList<>();
        ArrayList<LineDataStorage> lineArr = new ArrayList<>();

        for (SlideDataStorage slide : slideshowArr) {
            for (Iterator<AudioDataStorage> iter = slide.audioIterator(); iter.hasNext(); ) {
                audio = iter.next();
                audioArr.add(audio);
            }
            for (Iterator<ShapeDataStorage> iter = slide.shapeIterator(); iter.hasNext(); ) {
                shape = iter.next();
                shapeArr.add(shape);
            }
            for (Iterator<LineDataStorage> iter = slide.lineIterator(); iter.hasNext(); ) {
                line = iter.next();
                lineArr.add(line);
            }
        }
        AudioPlayer myPlayer = new AudioPlayer();
        myPlayer.loadClip(String.valueOf(audioArr.get(0).getAudioLocation()).replace("file://", ""));
        myPlayer.playClip(false);

        GraphicsPanel gp = new GraphicsPanel();
        JFrame f = new JFrame();                            //Create new JFrame, acting as the main window
        f.setVisible(true);                                 //Set the frame to visible
        f.setSize(1600, 900);                   //Set size to 1600x900 pix
        f.setTitle("Audio & Graphics Demo");                //Set frame title to "Graphics Test"
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Will exit out when close button pressed

        f.add(gp);                                          //Add the GraphicsPanel object to the frame

        gp.addRect(shapeArr.get(0).getHeight(), shapeArr.get(0).getWidth(),
                   shapeArr.get(0).getxPosition(), shapeArr.get(0).getyPosition(),
                   shapeArr.get(0).getColourInHex(), true, shapeArr.get(0).getDuration());

        gp.addRect(shapeArr.get(1).getHeight(), shapeArr.get(1).getWidth(),
                   shapeArr.get(1).getxPosition(), shapeArr.get(1).getyPosition(),
                   shapeArr.get(1).getColourInHex(), false, shapeArr.get(1).getDuration());
        gp.addOval(shapeArr.get(2).getHeight(), shapeArr.get(2).getWidth(),
                shapeArr.get(2).getxPosition(), shapeArr.get(2).getyPosition(),
                shapeArr.get(2).getColourInHex(), true, shapeArr.get(2).getDuration());

        gp.addOval(shapeArr.get(3).getHeight(), shapeArr.get(3).getWidth(),
                shapeArr.get(3).getxPosition(), shapeArr.get(3).getyPosition(),
                shapeArr.get(3).getColourInHex(), false, shapeArr.get(3).getDuration());


        gp.addLine(lineArr.get(0).getX1(), lineArr.get(0).getY1(),
                lineArr.get(0).getX2(), lineArr.get(0).getY2(),
                lineArr.get(0).getColourInHex(), lineArr.get(0).getDuration());
        gp.addLine(lineArr.get(1).getX1(), lineArr.get(1).getY1(),
                lineArr.get(1).getX2(), lineArr.get(1).getY2(),
                lineArr.get(1).getColourInHex(), lineArr.get(1).getDuration());

        gp.repaint();

    }
}
