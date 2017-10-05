import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
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
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 */
public class FXMainTest extends Application {

    private static SLSystem CONTROLLER;

    private final static String FILE_NAME = "./res/xml/e learner self study.xml"; // TODO

    @Override
    public void start(Stage primaryStage) {
    }

    /**
     * Main method
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
//        launch(args);
        CONTROLLER = new SLSystem();
//        ArrayList<LessonList> units = CONTROLLER.getUnits(); // fully built-up units
        ArrayList<Unit> units = CONTROLLER.getUnits(); // fully built-up courses
        // System.out.println(courses.get(0).toString());

        System.exit(0);
    }

    // On lesson list screen
//    private static void selectLesson(String selectedLessonID){
//        Lesson lesson = CONTROLLER.getLesson(selectedLessonID);
//        if (lesson == null) ; // do something
//        else System.out.println(lesson.toString());
        // set view accordingly
//    }

    // On lesson screen
//    private static void selectActivity(String selectedScreenID){
//        LessonActivity la = CONTROLLER.getActivity(selectedScreenID);
//        if (la == null) ; // do something
//        else System.out.println(la.toString());
        // set view accordingly
//    }

    // On lesson activity screen
    private static void nextActivity(){
        LessonActivity la = CONTROLLER.getNextActivity();
        if (la == null) ; // do something
        // set view accordingly
    }

    // On lesson activity screen
    private static void prevActivity(){
        LessonActivity la = CONTROLLER.getPreviousActivity();
        if (la == null) ; // do something
        // set view accordingly
    }

    // On lesson activity screen
//    private static void units(){
//        Unit u = CONTROLLER.listLessons();
        // set view accordingly
//    }
}
