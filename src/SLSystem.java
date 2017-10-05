
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import models.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import xmlparser.XMLParser;

/**
 * Class representing controller of the application
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class SLSystem {

    private final static String FILE_NAME = "./res/xml/e learner self study.xml"; // start pos: SignLearnerDesktop/dist
    private ArrayList<Course> COURSES = new ArrayList<>();
    private ArrayList<Unit> UNITS = new ArrayList<>();

    private Course currCourse;
    private Unit currUnit;
    private Lesson currLesson;
    private LessonActivity currActivity;

    public SLSystem(){

        try{
            COURSES = this.build();
            // hardcoding for only functional Unit
            currCourse = COURSES.get(0);
            UNITS = currCourse.getUnits();
            currUnit = UNITS.get(0);
            
        }
        catch(IOException | SAXException | ParserConfigurationException e){
            // ?
        }
    }

//    public Lesson getLesson(String lessonID){
//        Lesson lesson = currLessonList.getLesson(lessonID);
//        if(lesson != null){
//            currLesson = lesson;
//            currActivity = lesson.getActivity(0);
//        }
//        return lesson;
//    }
//
//    public LessonActivity getActivity(String lessonActivityID){
//        LessonActivity la = currLesson.getActivity(lessonActivityID);
//        if (la != null) currActivity = la;
//        return la;
//    }

    public LessonActivity getNextActivity(){
        LessonActivity la = currLesson.next();
        if (la != null) currActivity = la;
        return la;
    }

    public LessonActivity getPreviousActivity(){
        LessonActivity la = currLesson.previous();
        if (la != null) currActivity = la;
        return la;
    }

    public Unit listUnits(){
        currLesson = null;
        currActivity = null;

        return currUnit;
    }
    
    /**
     * Uses SAXParserFactory to parse XML input (from Authoring Tool) and produces
     * curriculum structure
     * @return an ArrayList<Course> of all Course objects created from parsing XML
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException 
     */
    private ArrayList<Course> build() throws IOException, SAXException, ParserConfigurationException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        XMLParser xmlp = new XMLParser();
        xmlReader.setContentHandler(xmlp);
        xmlReader.parse(convertToFileURL());

        return xmlp.getCourses();
    }

    /**
     * Resolves URL of absolute address of file, taken from: <a href='https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html'>Java SAX Documentation</a>
     * @return resolved URL String
     */
    private static String convertToFileURL() {
        String path = new File(FILE_NAME).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    public ArrayList<Unit> getUnits(){
        return UNITS;
    }
}
