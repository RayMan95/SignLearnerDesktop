/*
 * LessonListController - displays listed lessons and enables user to selecr a  
 * lesson. On selection of a lesson  stage transitions to specified lesson screen
 * @author: Luveshen Pillay
 * @date: 3/10/2017
 */
package LessonListView;

import Controller.SLSystem;
import LessonView.LessonViewController;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Lesson;
import models.LessonList;

/**
 * FXML Controller class
 *
 * @author Luveshen
 */
public class LessonListController implements Initializable {

    SLSystem slsystem;
    LessonList lessonList;

    @FXML
    private JFXListView<Label> listView;

    @FXML
    private TextField searchField;

    /**
     * Increases listView font size to 16
     *
     * @param event
     */
    @FXML
    private void zoomIn(ActionEvent event) {
        listView.setStyle("-fx-font-size: " + 16);

    }

    /**
     * Decreases listView font size to 12
     *
     * @param event
     */
    @FXML
    private void zoomOut(ActionEvent event) {
        listView.setStyle("-fx-font-size: " + 12);
    }

    /**
     * Opens up a lessonview (screen containing lesson activities), based on
     * which lesson was chosen
     *
     * @param event
     */
    @FXML
    private void loadLessons(MouseEvent event) {
        try {
            String title = listView.getSelectionModel().getSelectedItem().getText();
            System.out.println("TITLE: "+title);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LessonView/LessonView.fxml"));
            // Change scene to Lesson view
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene((Pane)loader.load());
            
            LessonViewController controller
                    = loader.<LessonViewController>getController();
            // Set lesson to be presented in lessonView 
            controller.initData(lessonList.getLessonbyTitle(title));
            stage.setScene(scene);
            stage.show();
        } 
        
        catch(NullPointerException n){
            System.out.println("Blank clicked");
        }
        
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            
            // Report exception to user in Alert Box
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lesson not found! Please contact the course administrator for help.");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();

        }
        

    }

    /**
     * Initializes the controller class by populating the listview with all the lessons 
     * contained in the SLSystem. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        slsystem = new SLSystem();
        lessonList = slsystem.listLessons();
        ObservableList<Label> data = FXCollections.observableArrayList();//

        ArrayList<Lesson> arr = lessonList.getAll();
        for (Lesson l : arr) {
            Label lbl = new Label(l.getTitle());
            data.add(lbl);
        }

        FilteredList<Label> filteredData = new FilteredList<>(data, s -> true);

       // Enables searhc functionaliy for lessons
        searchField.textProperty().addListener(obs -> {
            String filter = searchField.getText();
            if (filter == null || filter.length() == 0) {
                filteredData.setPredicate(s -> true);
            } else {
                filteredData.setPredicate(s -> s.getText().contains(filter));
            }
        });

        listView.setItems(filteredData);

    }

}
