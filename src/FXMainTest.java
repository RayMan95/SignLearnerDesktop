/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import xmlparser.XMLParser;
import models.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Ray
 */
public class FXMainTest extends Application {
    
    private final static String FILE_NAME = "../icdl/xml/e learner self study.xml"; // start pos: SignLearnerDesktop/dist
    
    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
//        launch(args);
        
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        
        XMLReader xmlReader = saxParser.getXMLReader();
        XMLParser xmlp = new XMLParser();
        xmlReader.setContentHandler(xmlp);
        xmlReader.parse(convertToFileURL());
        
        for (Lesson l : xmlp.lessons)
            System.out.println(l.toString());
        System.exit(0);
    }

    /**
     * Resolves URL of absolute address of file
     * @return 
     */
    
    public static String convertToFileURL() {
        String path = new File(FILE_NAME).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
    
}
