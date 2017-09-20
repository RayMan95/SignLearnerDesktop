/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signlearner;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author basupi.bastos.Tapela
 */
public class SignLearnerController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button home;
    @FXML
    private Button lessons;
    @FXML
    private Button exit;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    /**
     * navigates user back to main screen
     * @param event@FXML
    public void clickHome(ActionEvent event) throws IOException{
        //build main view scene
        Parent root = FXMLLoader.load(getClass().getResource("SignLearner.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show(); 
    
    }
     * @throws java.io.IOException
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
    
    /**
     * navigates the user to a new scene, composed of a list of collection
     * of type lesson, 
     * @param event
     * @throws java.io.IOException
     */
    @FXML
    public void clickLessons(ActionEvent event) throws IOException{
        //build a new scene
        Parent root = FXMLLoader.load(getClass().getResource("lessonsView.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();  
    
    }
    
    /**
     * terminates the program
     */
    @FXML
    public void clickExit(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            System.exit(0);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
            }

}
