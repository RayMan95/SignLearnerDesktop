/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WelcomeScreen;

import Controller.SLSystem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Controller.*;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.*;
/**
 * FXML Controller class
 *
 * @author Luveshen
 */
public class WelcomeScreenController implements Initializable {
    
    SLSystem sls;
    Unit unit;
    @FXML
    private Button enterLessonListButton;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private String path="/video/Welcome screen.mp4";
    public WelcomeScreenController() {

    }
    /**
     * Resizes & transforms the stage and puts the lessonList scene on
     * @param event
     * @throws IOException 
     */
    @FXML
    public void enterLessonList(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LessonListView/LessonList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
 
        //set Stage boundaries to the lower right corner of the visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 400);
        stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight()-850);
        stage.setWidth(250);
        stage.setHeight(300);
        stage.setResizable(true);
        stage.show();
        
    }
    
    @FXML
    public void exit(){
        System.exit(0);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        Media media = new Media(("file:///"+System.getProperty("user.dir")+path).replace("\\","/").replaceAll(" ","%20"));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(true);
        mediaView.setMediaPlayer(mediaPlayer);
        
    }  

    
}
