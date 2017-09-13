/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalmedia;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Luveshen
 */
public class FinalMedia extends Application {
    SceneState scenestate;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scenestate=SceneState.CUSTOM;
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent clicks){
                if(clicks.getClickCount()==2&&(scenestate==SceneState.CUSTOM)){
                    stage.setFullScreen(true);
                    scenestate=SceneState.FULL;
                }
                else if(clicks.getClickCount()==2&&(scenestate==SceneState.FULL)){
                    stage.setFullScreen(false);
                    scenestate=SceneState.CUSTOM;
                }
            }
            
        });
     
        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.setMinHeight(200);
                
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
