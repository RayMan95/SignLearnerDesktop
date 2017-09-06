/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;


/**
 *
 * @author basupi.bastos.Tapela
 */
public class FXMLDocumentController implements Initializable {
   
   @FXML
   private MediaView mediaView;
   @FXML
   MediaPlayer mediaPlayer;
           
   @FXML 
   private Button Play;
   @FXML
   private Button Pause;
   @FXML
   private Button Exit; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Media> videoLlist = new ArrayList<Media>();//contain video url's
        
        
        String VideoURL = "file:/C:/Users/basupi.bastos.Tapela/Desktop/output_video/structure.mp4";
        Media media = new Media(VideoURL); //intialize media object
        mediaPlayer = new MediaPlayer(media);//intialize media playe
        mediaView.getFitWidth();
        mediaView.getFitHeight();
        mediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    //plays the loaded video URL once invoked, restatrts videos if invoked while video is in play
    private void clickPlay(){
        if(mediaPlayer.getStatus().equals(Status.PLAYING)){
            mediaPlayer.stop();
            mediaPlayer.play();
        }
        
        else{    mediaPlayer.play();}
    }
    
    @FXML
    //pauses video 
    private void clickPause(){
        mediaPlayer.pause();
        
    }
    
    @FXML
    private void clickExit(){
       // mediaPlayer.
    }
    
}
