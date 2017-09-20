/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signlearner;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author basupi.bastos.Tapela
 */
public class LessonsViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<String> lessonView;
    @FXML
    private Button play;
    @FXML
    private Button home;
    @FXML
    private ListView<Integer> lessonDetail;
    private ArrayList<String> list = new ArrayList<String>();//contains list of key lessons
    
    //hashmap data structure, to map selected value to its observableList.
    private HashMap<String,ObservableList<Integer>> randomHashMap = new HashMap<String,ObservableList<Integer>>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //generic code to be replaced and popolated with lesson collection generated
        //from the xml parser
        
        //list of lessons
        list.addAll(Arrays.asList("Lesson 1", "Lesson 2",
                "Lesson 3", "Lesson 4", "Lesson 5","Lesson 6", "Lesson 7", "Lesson 8"));
        
        //populate the hashMap with generated ObservableList<Integer>
        list.forEach((i) -> {
            randomHashMap.put(i, generateIntList());
        });
        //populate the lessons listView table
        ObservableList<String> data = FXCollections.observableArrayList("Lesson 1", "Lesson 2",
                "Lesson 3", "Lesson 4", "Lesson 5","Lesson 6", "Lesson 7", "Lesson 8");
        lessonView.setItems(data);
        //lessonDetail.setItems(generateIntList());
        
        
    } 
    
    /**
     * display tasks for the selected Lesson
     * to be replaced by Object Lesson from xml parser
     */
    public void displayLessonDetails(){
        /**
         * key value
            randomly assigns the lesson detail list view a set of randomly generated 
            integers... 
            TO BE REPLACED BY GENERATING DETAILS FROM THE PARSED LESSON DETAILS...
            e.g placement of tasks from a lesson..
          *stored in a hashmap<LESSON, LESSON_DETAILS> 
         */
        
        lessonDetail.setItems(randomHashMap.get(lessonView.getSelectionModel().getSelectedItem()));
        
    }
    
    
    //tester method to generate list to populate list view...
    //TO BE REPLACED ONCE INTEGRATION OF COMPONENTS TAKES PLACE
    //used as a place-holder for values in the list view component.
    public ObservableList<Integer> generateIntList(){
        Random random = new Random();
        ObservableList<Integer> data = FXCollections.observableArrayList();
        int limit = random.nextInt(20);
        for(int i=0; i<limit; i++){
            data.add(i);
        }
        return data;
    }
    
    /**
     * play button selecting to play the selected task from the lesson detail listview
     * @param event
     * @param object
     * @throws IOException 
     */
    @FXML
    public void playTask(ActionEvent event) throws IOException{
        //if no task video has been selected, an alert pop up for the user
        if((lessonDetail.getSelectionModel().selectedItemProperty().getValue()) == null){
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            //alert.setHeaderText("Warning Dialog");
            alert.setContentText("Please select a task to play");

            alert.showAndWait();
            
        }
        else{
            //System.out.println("Now playing video"+lessonDetail.getSelectionModel().selectedItemProperty().getValue());
            //lessonDetail.getSelectionModel().clearSelection();
            Parent root = FXMLLoader.load(getClass().getResource("LessonPlayerView.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show(); 
            
        }
    }
    
    /**
     * @clickHome --> navigates the user back to the main menu screen(stage)
     * @param event
     * @throws IOException 
     */
    @FXML
    public void clickHome(ActionEvent event) throws IOException{
        //build main view scene
        Parent root = FXMLLoader.load(getClass().getResource("SignLearner.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show(); 
    
    }
    
}
