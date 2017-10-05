/*
 * LessonViewController - displays listed lesson activities, on user activity 
 * selection,transitions to VideoLessonPlayer screen lesson.
 * @author: Luveshen Pillay
 * @date: 3/10/2017
 */
package LessonView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ActivityPlayerView.VideoFXMLController;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Lesson;
import models.LessonActivity;

/**
 * FXML Controller class
 *
 * @author Luveshen
 */
public class LessonViewController implements Initializable {
    Lesson lesson;
    @FXML
    private JFXListView<Label> listView;
    @FXML 
    private BorderPane borderPane;
    @FXML
    private JFXButton zoomInButton;
    @FXML
    private JFXButton zoomOutButton;
    @FXML
    private TextField searchField;
    /**
     * Initializes the controller class, Kept blank as LessonList controller sets
     * the lesson activity context for the scene.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    /**
     * Populates listView with lesson activities
     * @param lesson - lesson whose activities are going to be viewed
     */
    public void initData(Lesson lesson){
        this.lesson=lesson;

        ArrayList<String> arr = lesson.getLessonActivities();

        ObservableList<Label> data = FXCollections.observableArrayList();//
        for(String l:arr){
            Label lbl = new Label(l);
            data.add(lbl);
        }
        
        // Enables lesosn activity search functionality 
        FilteredList<Label> filteredData = new FilteredList<>(data, s -> true);
        searchField.textProperty().addListener(obs->{
        String filter = searchField.getText(); 
        if(filter == null || filter.length() == 0) {
            filteredData.setPredicate(s -> true);
        }
        else {
            filteredData.setPredicate(s -> s.getText().contains(filter));
        }
    });
    
    listView.setItems(filteredData);
        
        
    }
    /**
     * Opens a LessonList scene chosen through user click and transfers control to
     * the new scene
     * @param event
     * @throws IOException 
     */
    @FXML
    private void backToLessons(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LessonListView/LessonList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        
    }
    /**
     * Increases listView font size to 16
     * @param event 
     */
    @FXML
    private void zoomIn(ActionEvent event){
        listView.setStyle("-fx-font-size: "+16);
    }
     /**
     * Decreases listView font size to 12
     * @param event 
     */
    @FXML
    private void zoomOut(ActionEvent event){
        listView.setStyle("-fx-font-size: "+12);
    }
    
    /**
     * Loads chosen lesson into VideoFXML controller and transfers control to 
     * the controller
     * @param event
     * @throws IOException 
     */
    @FXML
    private void loadLessonActivity(MouseEvent event ) throws IOException{
        try{
        LessonActivity activity;
        String title = listView.getSelectionModel().getSelectedItem().getText();
        System.out.println("HE CLICKED ME: " +title );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ActivityPlayerView/VideoFXML.fxml" ));
        
        // Load activity into controller 
        Parent root = (Parent)loader.load();
        VideoFXMLController controller = loader.getController();
        System.out.println(controller);
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
        controller.setActivity(lesson,lesson.getActivity(title));
        
        // Transfer control
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SignLearner - "+title);
        stage.setScene(new Scene (root));
        stage.show();
        }
        
        catch(NullPointerException n){
            System.out.println("Blank clicked");
        }
        
        catch(Exception e){
            // Report errors to the user    
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lesson Activity not configured properly - video not found. Please contact the course administrator for help.");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
        }
        
    }
    
}
