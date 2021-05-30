/* 
 * Company name: String Theory
 * 
 * Class Name: MediaControl
 * 
 * File Version: 1.0
 * 
 * Authors: John Bateman
 * 
 * Dependencies: JavaFX and JavaFX.Media (included in pom.xml for Maven)
 * 
 * Date Created: 15/03/21
 * 
 * Date Last Updated: 17/03/21
 */

package control;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

// This class is used in place of the MediaView class that is packaged with JavaFX,
// it displays the MediaPlayer object and builds the control panel for the video.
public class MediaControl extends BorderPane {
    private MediaPlayer mp;	
    private MediaView mediaView;
	private final boolean loop;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;

    /**
     * @param mp : A MediaPlayer object which contains the vide
     * @param repeat : A boolean value telling the MediaControl whether to loop
     * @param height : The desired height of the MediaControl object
     * @param width : The desired width of the MediaControl object
     * 
     * The constructor class for a MediaControl object, and the only method in
     * MediaControl which should be necessary to call externally. 
     */
    public MediaControl(final MediaPlayer mp, boolean repeat, int height, int width) {
        this.mp = mp;
        this.loop = repeat;
        resize(width, height);
        setStyle("-fx-background-color: #bfc2c7;");
        autosize();
        mediaView = new MediaView(mp);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);
        mediaView.setSmooth(true);
        Pane mvPane = new Pane() {                };
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;"); 
        setCenter(mvPane);
        mvPane.setPrefSize(width, height);
        mvPane.autosize();
        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
 
        final Button playButton  = new Button(">");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Status status = mp.getStatus();
         
                if (status == Status.UNKNOWN  || status == Status.HALTED)
                {
                   // Don't do anything if the status is unknown or halted
                   return;
                }
         
                  if ( status == Status.PAUSED
                     || status == Status.READY
                     || status == Status.STOPPED)
                  {
                     // Return to the beginning if the video has finished and loop is true
                     if (atEndOfMedia && loop) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                     }
                     mp.play();
                     } else {
                       mp.pause();
                     }
                 }
           });
        
        mp.currentTimeProperty().addListener(new InvalidationListener() 
        {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
 
        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });
 
        mp.setOnPaused(new Runnable() {
            public void run() {
                System.out.println("onPaused");
                playButton.setText(">");
            }
        });
 
        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });
 
        mp.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!loop) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
       });
        
        
        mediaBar.getChildren().add(playButton);
        setBottom(mediaBar); 
     // Add spacer
        Label spacer = new Label("   ");
        mediaBar.getChildren().add(spacer);
         
        // Add Time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);
         
        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider,Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
               if (timeSlider.isValueChanging()) {
               // multiply duration by percentage calculated by slider position
                  mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
               }
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);
         
        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);
         
        // Add Volume slider
        volumeSlider = new Slider();        
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
               if (volumeSlider.isValueChanging()) {
                   mp.setVolume(volumeSlider.getValue() / 100.0);
               }
            }
        });
        mediaBar.getChildren().add(volumeSlider);
        this.autosize();
     }
    
    /**
     * A method which updates the value of the time slider to represent the video playing
     */
    protected void updateValues() {
    	  if (playTime != null && timeSlider != null && volumeSlider != null) {
    	     Platform.runLater(new Runnable() {
    	        @SuppressWarnings("deprecation")
				public void run() {
    	          Duration currentTime = mp.getCurrentTime();
    	          Duration duration = mp.getCycleDuration();
    	          playTime.setText(timeToString(currentTime, duration));
    	          timeSlider.setDisable(duration.isUnknown());
    	          if (!timeSlider.isDisabled() 
    	            && duration.greaterThan(Duration.ZERO) 
    	            && !timeSlider.isValueChanging()) {
    	              timeSlider.setValue(currentTime.divide(duration).toMillis()
    	                  * 100.0);
    	          }
    	          if (!volumeSlider.isValueChanging()) {
    	            volumeSlider.setValue((int)Math.round(mp.getVolume() 
    	                  * 100));
    	          }
    	        }
    	     });
    	  }
    	}
    	
    /**
     * @param elapsed
     * @param duration
     * @return
     * 
     * This method formats a Duration object and changes it into a String which can be
     * displayed on the time bar
     */
    private static String timeToString(Duration elapsed, Duration duration) {
    	   int intElapsed = (int)Math.floor(elapsed.toSeconds());
    	   int elapsedHours = intElapsed / (60 * 60);
    	   if (elapsedHours > 0) {
    	       intElapsed -= elapsedHours * 60 * 60;
    	   }
    	   int elapsedMinutes = intElapsed / 60;
    	   int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 
    	                           - elapsedMinutes * 60;
    	 
    	   if (duration.greaterThan(Duration.ZERO)) {
    	      int intDuration = (int)Math.floor(duration.toSeconds());
    	      int durationHours = intDuration / (60 * 60);
    	      if (durationHours > 0) {
    	         intDuration -= durationHours * 60 * 60;
    	      }
    	      int durationMinutes = intDuration / 60;
    	      int durationSeconds = intDuration - durationHours * 60 * 60 - 
    	          durationMinutes * 60;
    	      if (durationHours > 0) {
    	         return String.format("%d:%02d:%02d/%d:%02d:%02d", 
    	            elapsedHours, elapsedMinutes, elapsedSeconds,
    	            durationHours, durationMinutes, durationSeconds);
    	      } else {
    	          return String.format("%02d:%02d/%02d:%02d",
    	            elapsedMinutes, elapsedSeconds,durationMinutes, 
    	                durationSeconds);
    	      }
    	      } else {
    	          if (elapsedHours > 0) {
    	             return String.format("%d:%02d:%02d", elapsedHours, 
    	                    elapsedMinutes, elapsedSeconds);
    	            } else {
    	                return String.format("%02d:%02d",elapsedMinutes, 
    	                    elapsedSeconds);
    	            }
    	        }
    	    }
    
}