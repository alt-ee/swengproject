
/*
 * Company name: String Theory
 *
 * Class Name: Video
 *
 * File Version: 0.9
 *
 * Authors: John Bateman
 *
 * Dependencies: JavaFX, JavaFX.Swing and JavaFX.Media (included in pom.xml for Maven)
 *
 * Date Created: 08/03/21
 *
 * Date Last Updated: 17/03/21
 */

// Package statement
package media;

// Import Statement
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.MediaControl;
import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This is a preliminary implementation of the Video class, which acts as a container for a JPanel
 * that displays the referenced video file. Currently, the video is created with the constructor call:
 *
 * Video testVideo = new Video(testFrame,"video location", starttime, loop, xstart, ystart, layoutConstraints, width, height);
 *
 * The width value is currently unused, as the video is automatically rendered in 16:9 aspect ratio from the height value.
 * With the video created, calling the following method:
 *
 * testVideo.startVideo()
 *
 * Will sleep on the thread for starttime seconds before playing the video.
 *
 */
public class Video {
	// A class which contains all the video data from the XML file, and can output it to a JFrame
	private String urlname;
	private int starttime;
	private boolean loop;
	private int xstart;
	private int ystart;
	private int width;
	private int height;
	private boolean local;
	private JPanel videoPanel;
	MediaPlayer player;


	/**
	 * Constructor for a video object In a JFrame
	 *
	 * @param frame : The JFrame to place the videoPanel in
	 * @param urlname : The location of the video file to play (either as a url or a local path)
	 * @param starttime : The time delay before the start of the video
	 * @param loop : Whether or not to loop the video
	 * @param xstart : The x-coordinate for the top left of the video panel
	 * @param ystart : The y-xoordinate for the top left of the video panel
	 * @param layoutConstraints : The Constraints object for where in the JFrame layout to place the video
	 * @param width : The desired width of the video
	 * @param height : The desired height of the video
	 * @Param local : A boolean for whether the urlname is a filepath or a url
	 * @param autoCalculateWidth : A variable that indicates whether to auto calculate the width of the video from the height as a 16:9 ratio, or to stretch the video
	 */
	public Video (JFrame frame, String urlname, int starttime, boolean loop, int xstart, int ystart, Object layoutConstraints, int width, int height, Boolean local, Boolean autoCalculateWidth)
	{
		videoPanel = new JPanel();
		this.urlname = urlname;
		this.starttime = starttime;
		this.loop = loop;
		this.xstart = xstart;
		this.ystart = ystart;
		local = true;
		this.height = height;
		if (autoCalculateWidth == true) {
			this.width = (int) 16*height/9; // Calculating width as a 16:9 aspect ratio from the height
		}
		else {
			this.width = width;
		}
		this.local = local;
		videoPanel.setBounds(xstart,ystart,width, height);
		getVideo();
		frame.add(videoPanel, layoutConstraints);
	}

	/**
	 * Constructor for a video object In a JPanel
	 *
	 * @param panel : The JPanel to place the videoPanel in
	 * @param urlname : The location of the video file to play (either as a url or a local path)
	 * @param starttime : The time delay before the start of the video
	 * @param loop : Whether or not to loop the video
	 * @param xstart : The x-coordinate for the top left of the video panel
	 * @param width : The desired width of the video
	 * @param height : The desired height of the video
	 * @Param local : A boolean for whether the urlname is a filepath or a url
	 *  @param autoCalculateWidth : A variable that indicates whether to auto calculate the width of the video from the height as a 16:9 ratio, or to stretch the video
	 */
	public Video (JPanel panel, String urlname, int starttime, boolean loop, int xstart, int ystart, int width, int height, Boolean local, Boolean autoCalculateWidth)
	{
		videoPanel = panel;
		this.urlname = urlname;
		this.starttime = starttime;
		this.loop = loop;
		this.xstart = xstart;
		this.ystart = ystart;
		local = true;
		this.height = height;
		if (autoCalculateWidth == true) {
			this.width = (int) 16*height/9; // Calculating width as a 16:9 aspect ratio from the height
		}
		else {
			this.width = width;
		}
		this.local = local;
		videoPanel.setBounds(xstart,ystart,width, height);
		getVideo();
	}

	/**
	 * This function locates the video file from the urlname object, adds it to a JFXPanel,
	 * and adds that to the videoPanel JPanel
	 */

	private synchronized void getVideo() {
		JFXPanel VFXPanel = new JFXPanel();
		local = true;
		VFXPanel.setSize(width, height);
		Media video;
		if (local == true) {
			// When urlname actually refers to a file path
			File videoFile = new File(urlname);
			video = new Media(videoFile.toURI().toString());
		} else {
			// When urlname refers to an actual url
			video = new Media(urlname);
		}
		player = new MediaPlayer(video);
		player.setAutoPlay(false);

		StackPane root = new StackPane();
		Scene scene = new Scene(root, width,height, Color.AZURE);
		MediaControl viewer = new MediaControl(player,loop, height, width);

		viewer.setVisible(true);
		root.getChildren().add(viewer);
		VFXPanel.setScene(scene);
		videoPanel.add(VFXPanel);
	}

	/**
	 * This function sleeps the thread for starttime seconds, and then
	 * starts the video. Running this method is how other classes should
	 * start the video object
	 */
	public void startVideo() {
		try {
			Thread.sleep(starttime*1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted, starting video");
		}
		player.play();
	}

	public void destroyVideo() {
		player.stop();
		player.dispose();
		videoPanel.invalidate();
	}

	// Getters and Setters for the variables
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public boolean isLoop() {
		return loop;
	}
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	public int getXstart() {
		return xstart;
	}
	public void setXstart(int xstart) {
		this.xstart = xstart;
	}
	public int getYstart() {
		return ystart;
	}
	public void setYstart(int ystart) {
		this.ystart = ystart;
	}

    public JPanel getVideoPanel() {
		return videoPanel;
	}


}
