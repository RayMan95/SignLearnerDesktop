/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalmedia;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 *
 * @author Luveshen
 */
public class FXMLDocumentController implements Initializable {

    private MediaPlayer mediaPlayer;
    private double playSpeed;
    private MediaPlayer.Status status;
    private Duration duration;// = mediaPlayer.getStatus();
// 1 B 2BC 3D 4non-functional 5Conceptual views 6B 7Client-Server 8E 9A 10B 11B 12B

    @FXML
    private MediaView mediaView;
    @FXML
    private JFXButton playPauseButton;
    @FXML
    private VBox controlBox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXSlider timeSlider ;
    @FXML
    private Label playTime;
   
    
    

    /**
     * Method to toggle between paused states and the playing of videos in the
     * mediaplayer TO DO: change button & text
     *
     * @param event - mouseclick event triggers the toggle
     */
    @FXML
    private void playpauseVideo(ActionEvent event) {
        MediaPlayer.Status test = mediaPlayer.getStatus();
        try {
            if (test == MediaPlayer.Status.UNKNOWN
                    || status == MediaPlayer.Status.HALTED) {
                // don't do anything in these states
                return;
            }
            if (test == MediaPlayer.Status.PLAYING) {
                //playPauseButton.getStyleClass().clear();
                System.out.println("Hello worldA");
                mediaPlayer.pause();
                changeIcon(0);
            } else if (test == MediaPlayer.Status.PAUSED
                    || test == MediaPlayer.Status.STOPPED
                    || test == MediaPlayer.Status.READY) {

                changeIcon(1);
                mediaPlayer.play();
                mediaPlayer.setRate(1);
            }
            

            //playPauseButton.setStyle("-fx-background-color: #32ff00 ");
            //playPaudeButton.setStyle()
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            /* Due to the use of JFoenix, JavaFx media player will throw an exception on the first use of the .play() method,
               this is to be expected and is MediaPlayer status dependent*/
        }

    }
    
    private void changeIcon(int mode){
        if(mode== 0){
              playPauseButton.setStyle("-fx-background-image: url('finalmedia/Icons/play.png')");
        }
        else if(mode == 1){
              playPauseButton.setStyle("-fx-background-image: url(\"finalmedia/Icons/pause.png\");");

        }
        
    }

    /**
     * Stops the video using the java stop method TO DO: consider graphic
     * changing of symbol!
     *
     * @param event - mouseclick event triggers the stop
     */
    @FXML
    private void stopVideo(ActionEvent event) {
        mediaPlayer.stop();
        changeIcon(0);

    }

    /**
     * Increases the playback speed to 1.5x in relation to the normal playback
     *
     * @param event - mouse click event triggers the speed increase
     */
    @FXML
    private void fastVideo(ActionEvent event) {
        mediaPlayer.setRate(1.5);
    }

    /**
     * Increases the playback speed to 2.0x in relation to the normal playback
     *
     * @param event - mouse click event triggers the speed increase
     */
    @FXML
    private void fasterVideo(ActionEvent event) {
        mediaPlayer.setRate(2);

    }

    /**
     * Decreases the playback speed by 0.75x in relation to the normal playback
     *
     * @param event - mouse click event triggers the speed increase
     */
    @FXML
    private void slowVideo(ActionEvent event) {
        mediaPlayer.setRate(.75);
    }

    /**
     * Decreases the playback speed by 0.5x in relation to the normal playback
     *
     * @param event - mouse click event triggers the speed increase
     */
    @FXML
    private void slowerVideo(ActionEvent event) {
        mediaPlayer.setRate(.5);

    }
    
    protected void updateValues() {
        //System.out.println("!@@#$");
        //System.out.println(playTime+" "+timeSlider+" "+duration );
        if (playTime != null && timeSlider != null && duration != null){
                    //System.out.println("XXXX");
                  
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                //timeSlider.setLabelFormatter(indicatorFormat());
                timeSlider.setDisable(duration.isUnknown());
                if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                    timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                }
            });
        }
    }

    private String formatTime(Duration elapsed, Duration duration) {
            int intElapsed = (int)Math.floor(elapsed.toSeconds());
            int elapsedHours = intElapsed / (60 * 60);
            if (elapsedHours > 0) {
                intElapsed -= elapsedHours * 60 * 60;
            }
            int elapsedMinutes = intElapsed / 60;
            int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

            if (duration.greaterThan(Duration.ZERO)) {
                int intDuration = (int)Math.floor(duration.toSeconds());
                int durationHours = intDuration / (60 * 60);
                if (durationHours > 0) {
                    intDuration -= durationHours * 60 * 60;
                }
                int durationMinutes = intDuration / 60;
                int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

                if (durationHours > 0) {
                    return String.format("%d:%02d:%02d/%d:%02d:%02d",
                                         elapsedHours, elapsedMinutes, elapsedSeconds,
                                         durationHours, durationMinutes, durationSeconds);
                } else {
                    return String.format("%02d:%02d/%02d:%02d",
                                         elapsedMinutes, elapsedSeconds,
                                         durationMinutes, durationSeconds);
                }
            } else {
                if (elapsedHours > 0) {
                    return String.format("%d:%02d:%02d",
                                         elapsedHours, elapsedMinutes, elapsedSeconds);
                } else {
                    return String.format("%02d:%02d",
                                         elapsedMinutes, elapsedSeconds);
                }
            }
        }
        
    private String formatSliderTime(Duration elapsed,Duration duration){
        return formatTime(elapsed,duration).split("/")[0];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //C:/Users/Documents/Player/src/SampleVideo_1280x720_10mb.mp4"
        Media media = new Media("file:///filePathHere");
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        //NB change size PRIOR to displaying +> here is where we decide intial display value 
        //DO NOT TOUCH THE FXML!
      
        
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));  
        
       
        
        mediaPlayer.play();
        playSpeed = 1;
     
        
        mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
            updateValues();
        });
        
         mediaPlayer.setOnReady(() -> {
             duration = mediaPlayer.getMedia().getDuration();
             updateValues();
             System.out.println("HEY");
        });
            
        timeSlider.valueProperty().addListener((Observable ov) -> {
            if (timeSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                if(duration!=null) {
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    System.out.println("SEEKE");
                }
                Duration currentTime = mediaPlayer.getCurrentTime();
                 timeSlider.setValueFactory(slider ->
	  		Bindings.createStringBinding(
	  			() -> (formatSliderTime(currentTime, duration)),
	  			slider.valueProperty()
	  		)
                 );
                updateValues();
                
            }
        });
        
    }

}
