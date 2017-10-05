/*
 * WORK WITH HIS ONE change this license header, choose License Headers in Project Properties..
 */
package ActivityPlayerView;

import LessonView.LessonViewController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Lesson;
import models.LessonActivity;

/**
 *
 * @author Luveshen WORK HERE
 */
public class VideoFXMLController implements Initializable {

    private MediaPlayer mediaPlayer;
    private double playSpeed;
    private ChangeListener<Duration> progressChangeListener;

    private MediaPlayer.Status status;
    private Duration duration;// = mediaPlayer.getStatus();
// 1 B 2BC 3D 4non-functional 5Conceptual views 6B 7Client-Server 8E 9A 10B 11B 12B
    private String mediaFile = "C:/Users/Luveshen/Documents/Player/src/SampleVideo_1280x720_10mb.mp4";
    @FXML
    private MediaView mediaView;
    @FXML
    private JFXButton playPauseButton;
    @FXML
    private VBox controlBox;
    @FXML
    private VBox peripheralBox;
    /*@FXML
    private BorderPane borderPane;*/
    @FXML
    private JFXSlider timeSlider;
    @FXML
    private Label playTime;
    @FXML
    private JFXButton nextButton;
    @FXML
    private JFXButton previousButton;
    @FXML 
    private ImageView imageView;
    @FXML 
    private Group group;

    private Lesson lesson;
    private LessonActivity activity;
    private boolean atEndOfMedia = false;
    private boolean stopRequested = false;
    private boolean repeat=true;
  
    /**
     * Sets the activity (and lesson which contains it) to play
     *
     * @param l
     * @param la
     */
    public void setActivity(Lesson l, LessonActivity la) throws Exception {
        lesson = l;
        activity = la;
       System.out.println("BETTER NOT");
        setVideo(activity.getVideoUrl().toString().replaceAll(" ", "%20"));
     
        System.out.println("I RETURNERS");
        if (!lesson.hasNext()) {
            nextButton.setStyle("-fx-background-color: #ffffff");
        } else {
            nextButton.setStyle("WelcomeScreen/style.css");

        }

        if (!lesson.hasPrevious()) {
            previousButton.setStyle("-fx-background-color: #ffffff");

        } else {
            previousButton.setStyle("WelcomeScreen/style.css");
        }
        System.out.println("WHY ???");
        /*Image img = new Image("/MainWindow/Close.png");
                System.out.println("WHY FFFF");
        imageView.setImage(img);*/
                System.out.println("WHY NPT");
        System.out.println(" XXXX");

    }

    /**
     * Changes the media content to the next Activity video in the lesson
     * sequence on click TODO: exception handling where video does not exist
     * (POP UP)
     *
     * @param event
     */
    @FXML
    private void nextActivity(ActionEvent event) {
       try{
        mediaPlayer.pause();
        if (lesson.hasNext()) {
            setActivity(lesson, lesson.getNext());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("SignLearner - "+activity.getScreenID());

        } else {
           // System.out.println("@ DA END");
           new Alert(Alert.AlertType.INFORMATION, "This is the last Activity in the Lesson Sequence!").showAndWait();
        }
       }
       
       catch(Exception e){
                       StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lesson Activity not configured properly - video not found. Please contact the course administrator for help.");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            //this.backToActivity(new Event());
            //mediaPlayer.play();
            
       }
    }

    /**
     * Changes the media content to the next Activity video in the lesson
     * sequence on click TODO: exception handling where video does not exist
     * (POP UP)
     *
     * @param event
     */
    @FXML
    private void previousActivity(ActionEvent event) {
        mediaPlayer.pause();
        try {
            if (lesson.hasPrevious()) {
                setActivity(lesson, lesson.getPrevious());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("SignLearner - " + activity.getScreenID());
            } else {
                System.out.println("@ DA START");
                new Alert(Alert.AlertType.INFORMATION, "This is the first Activity in the Lesson Sequence!").showAndWait();
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lesson Activity not configured properly - video not found. Please contact the course administrator for help.");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            playPauseButton.setStyle("-fx-background-image: url('WelcomeScreen/Icons/play.png')");
            //this.backToActivity(new Event());
            //mediaPlayer.play();
            

        }

    }

    /**
     * Method to toggle between paused states and the playing of videos in the
     * mediaplayer TO DO: change button & text
     *
     * @param event - mouseclick event triggers the toggle
     */
    @FXML
    private void playpauseVideo(ActionEvent event) {
        //MediaPlayer.Status test = mediaPlayer.getStatus();
        try {
                  updateValues();
                    MediaPlayer.Status status = mediaPlayer.getStatus();
                    if (status == MediaPlayer.Status.UNKNOWN
                        || status == MediaPlayer.Status.HALTED)
                    {
                        // don't do anything in these states
                        return;
                    }

                    if (status == MediaPlayer.Status.PAUSED
                        || status == MediaPlayer.Status.READY
                        || status == MediaPlayer.Status.STOPPED)
                    {
                        // rewind the movie if we're sitting at the end
                        if (atEndOfMedia) {
                            mediaPlayer.seek(mediaPlayer.getStartTime());
                            atEndOfMedia = false;
                            //playButton.setGraphic(imageViewPlay);
                            //playButton.setText(">");
                            updateValues();
                        }
                        mediaPlayer.play();
                        changeIcon(1);
                        mediaPlayer.setRate(1);
                        //playButton.setGraphic(imageViewPause);
                        //playButton.setText("||");
                    }
                    else {
                        mediaPlayer.pause();
                        changeIcon(0);
                    }
        }
              catch(Exception e){
                      e.printStackTrace();
                      
        }

    }
    
    public void setImage(String path) throws Exception{
        
    }

    public void setVideo(String path) throws Exception{
        //try{
        System.out.println("FUARRRRRK Working Directory = "
                + System.getProperty("user.dir"));
        Media m = new Media("file:///" + System.getProperty("user.dir").replace("\\", "/") + path);
        if(m!=null){
            System.out.println("Path : " + m.getSource());
            System.out.println("REACHED 1");
            MediaPlayer mp = new MediaPlayer(m);
            System.out.println("REACHED 1");
            final MediaPlayer curPlayer = mediaView.getMediaPlayer();
            curPlayer.currentTimeProperty().removeListener(progressChangeListener);
            /*curPlayer.getMedia().getMetadata().removeListener(metadataChangeListener);
            curPlayer.stop();*/

            mediaView.setMediaPlayer(mp);
            //temp=mediaPlayer;
            //mediaPlayer.dispose();
            System.out.println("MEDIA: "+m);
            mediaPlayer = mp;
            mediaPlayer.currentTimeProperty().addListener(progressChangeListener);
            mediaPlayer.seek(mediaPlayer.getStartTime());
            //duration = mediaPlayer.getMedia().getDuration();
            updateValues();
            mediaPlayer.setMute(true);
            mediaPlayer.play();
            System.out.println("END OF HERE");
            
        }

        //}
        
       /* catch(Exception e){

            
        }*/

    }

    /**
     * Changes the icon on the play button
     *
     * @param mode
     */
    private void changeIcon(int mode) {
        if (mode == 0) {
            playPauseButton.setStyle("-fx-background-image: url('WelcomeScreen/Icons/play.png')");
        } else if (mode == 1) {
            playPauseButton.setStyle("-fx-background-image: url(\"WelcomeScreen/Icons/pause.png\");");

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
        //updateValues();
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

    @FXML
    private void replay(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();

    }

    /**
     * Loads a LessonList scene
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void backToLessons(ActionEvent event) throws IOException {
        this.stopVideo(event);
        Parent root = FXMLLoader.load(getClass().getResource("/LessonListView/LessonList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("SignLearner");

    }

    /**
     * Loads a LessonView scene containing all activities from the current
     * lesson being viewed
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void backToActivity(ActionEvent event) throws IOException {
        this.stopVideo(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LessonView/LessonView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        stage.setTitle("SignLearner");
        LessonViewController controller
                = loader.<LessonViewController>getController();
        controller.initData(lesson);
    }

    protected void updateValues() {
                  if (playTime != null && timeSlider != null && duration != null) {
                Platform.runLater(new Runnable() {
                    public void run() {
                       
                        duration = mediaPlayer.getMedia().getDuration(); //KEY LINE: seems to work        
                        Duration currentTime = mediaPlayer.getCurrentTime();
                        playTime.setText(formatTime(currentTime, duration));
                        timeSlider.setDisable(duration.isUnknown());
                        if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                            timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                        }
                    }
                });
            

        /*if (playTime != null && timeSlider != null && duration != null) {
            //System.out.println("XXXX");
            duration = mediaPlayer.getMedia().getDuration();
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                duration = mediaPlayer.getMedia().getDuration();
                //timeSlider.setLabelFormatter(indicatorFormat());
                timeSlider.setDisable(duration.isUnknown());
                if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                    timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                }
            });
        }*/
    }
    }

    /**
     * Formats time for label use
     *
     * @param elapsed
     * @param duration
     * @return
     */
    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
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
        } else if (elapsedHours > 0) {
            return String.format("%d:%02d:%02d",
                    elapsedHours, elapsedMinutes, elapsedSeconds);
        } else {
            return String.format("%02d:%02d",
                    elapsedMinutes, elapsedSeconds);
        }
    }

    /**
     * Formats time for slider use
     *
     * @param elapsed
     * @param duration
     * @return
     */
    private String formatSliderTime(Duration elapsed, Duration duration) {
        return formatTime(elapsed, duration).split("/")[0];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //C:/Users/Documents/Player/src/SampleVideo_1280x720_10mb.mp4"
       // Image img = new Image("/MainWindow/Close.png");
        Media media = new Media("file:///" + mediaFile);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(true);
        mediaView.setMediaPlayer(mediaPlayer);
        //NB change size PRIOR to displaying +> here is where we decide intial display value 
        //DO NOT TOUCH THE FXML!
       Image img = new Image("/WelcomeScreen/Close.png");
                System.out.println("WHY FFFF");
        imageView.setImage(img);
        //imageView.s
        //imageView.fitHeight()
        System.out.println("MOTOR EXPRESS");
        final Timeline slideIn = new Timeline();
        final Timeline slideOut = new Timeline();
        //controlBox.
        controlBox.setOnMouseExited((MouseEvent mouseEvent) -> {
            //double h = ((Node)mouseEvent.getSource()).getScene().getWindow().getHeight();
            /*System.out.println("TIme to hide");
            System.out.println(mediaPlayer.getMedia().getHeight());
            System.out.println(mediaView.getY());*/
            //slideOut.play();S3
        });
        controlBox.setOnMouseEntered((MouseEvent mouseEvent) -> {
            /*System.out.println("Here I am");
            System.out.println(mediaPlayer.getMedia().getHeight());
            System.out.println(mediaView.getY());*/
           // slideIn.play();
        });
        /*imageView.fitWidthProperty().bind(controlBox.widthProperty());
        imageView.fitHeightProperty().bind(controlBox.heightProperty());*/
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        //borderPane.
        //mediaView.getParent().sceneProperty();
                // borderPane.centerProperty().bind()
        width.bind(Bindings.selectDouble(mediaView.getParent().sceneProperty(), "width"));
        //height.bind(Bindings.selectDouble(mediaView.getParent().sceneProperty(), "height").subtract(150));
        height.bind(Bindings.selectDouble(mediaView.getParent().sceneProperty(), "height").subtract(peripheralBox.heightProperty()));
        //height.bind(Bindings.selectDouble(group.scaleXProperty().multiply(group.prefHeight())));
        //height.bind(Bindings.selectDouble(mediaView.getParent()., "width"); 
        //controlBox.heightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height").multiply(0.7)); 
        //controlBox.prefHeightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height").multiply(0.3));
        //mediaView.getParent().scaleXProperty();
        /* width.bind(Bindings.selectDouble(mediaView.getParent().sceneProperty(), "width"));   
         height.bind(Bindings.selectDouble(mediaView.getParent().sceneProperty(), "height"));
    /* mediaView.getParent().layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
        @Override
        public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
            mediaView.setFitHeight(newValue.getHeight());
            mediaView.setFitWidth(newValue.getWidth());
        }
    });*/
        //controlBox.

        mediaPlayer.play();
        playSpeed = 1;
        
        
       /*  progressChangeListener = new ChangeListener<Duration>() {
        @Override public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
          progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
        }
      };*/
       
        progressChangeListener =new ChangeListener<Duration>(){/*(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) ->*/ 
            @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    updateValues();
                }
        };
        
        mediaView.getMediaPlayer().currentTimeProperty().addListener(progressChangeListener);
                
       /* mediaView.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Duration>()/*(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) ->*/ {
            /*@Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    updateValues();
                }*/
            // System.out.println("POINT B");updateValues();System.out.println("FINISH");
            /*if (playTime != null && timeSlider != null && duration != null) {
                //System.out.println("XXXX");
                //duration = mediaPlayer.getMedia().getDuration();
                Platform.runLater(() -> {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    playTime.setText(formatTime(currentTime, mediaPlayer.getMedia().getDuration()));
                    // duration = mediaPlayer.getMedia().getDuration();
                    //timeSlider.setLabelFormatter(indicatorFormat());
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    }
                });
            }*/
        //});
         mediaPlayer.setOnPlaying(new Runnable() {
                public void run() {
                    
                    if (stopRequested) {
                        mediaPlayer.pause();
                        stopRequested = false;
                    } else {
                        //playButton.setGraphic(imageViewPause);
                        //playButton.setText("||");
                    }
                }
            });
            mediaPlayer.setOnPaused(new Runnable() {
                public void run() {
                    //playPauseButton.setStyle("-fx-background-image: url('finalmedia/Icons/p.png')");
                    //playButton.setGraphic(imageViewPlay);
                    //playButton.setText("||");
                }
            });

        mediaPlayer.setOnReady(/*() -> {*/
            //duration = mediaPlayer.getMedia().getDuration();
            new Runnable() {
                public void run() {
                    duration = mediaPlayer.getMedia().getDuration();
                    slideOut.getKeyFrames().addAll(new KeyFrame(new Duration(0), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.9)), new KeyFrame(new Duration(700), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.0)));
                    slideIn.getKeyFrames().addAll(new KeyFrame(new Duration(0), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.0)), new KeyFrame(new Duration(700), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.9)));
                    updateValues();
                }
           /* slideOut.getKeyFrames().addAll(new KeyFrame(new Duration(0), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.9)), new KeyFrame(new Duration(700), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.0)));

            slideIn.getKeyFrames().addAll(new KeyFrame(new Duration(0), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.0)), new KeyFrame(new Duration(700), new KeyValue(controlBox.translateYProperty(), mediaView.getY()), new KeyValue(controlBox.opacityProperty(), 0.9)));  */
            // System.out.println("HEY");
        });
        
         mediaView.getMediaPlayer().setOnEndOfMedia(new Runnable() {
                public void run() {
                    //if (!repeat) {
                        //playButton.setGraphic(imageViewPlay);
                        //playButton.setText(">");
                        //stopRequested = true;
                        //atEndOfMedia = true;
                        nextActivity(new ActionEvent());
                        System.out.println("WEEEEEEEEEEEEEEE");
                    //}
                }
            });
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    if (duration != null) {
                        mediaView.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0));
                    }
                    Duration currentTime = mediaView.getMediaPlayer().getCurrentTime();
                    timeSlider.setValueFactory(slider
                            -> Bindings.createStringBinding(
                                    () -> (formatSliderTime(currentTime, duration)),
                                    slider.valueProperty()
                            )
                    );
                    updateValues();

                }
            }
        });
       /* timeSlider.valueProperty().addListener(/*(Observable ov) -> {
            if (timeSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                if(duration!=null) {
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    //System.out.println("SEEKE");
                }
                Duration currentTime = mediaPlayer.getCurrentTime();
                 timeSlider.setValueFactory(slider ->
	  		Bindings.createStringBinding(
	  			() -> (formatSliderTime(currentTime, duration)),
	  			slider.valueProperty()
	  		)
                 );
                 System.out.println("Point A");
                //updateValues();
                
            }
        });*/
               /* new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });*/

    }

}
};
