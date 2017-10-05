package xmlparser;

import models.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;

/**
 * Class containing functionality for building course structures by parsing XML
 * files created by the Authoring Tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class XMLParser extends DefaultHandler {

    private final String KEY_COURSE = "course";
    private final String KEY_UNIT = "unit";
    private final String KEY_LESSON ="lesson";
    private final String KEY_SCREEN = "screen";
    private final String KEY_SCREENID = "screenID";
    private final String KEY_VIDEO = "video";
    private final String KEY_VIDEO_CAPTION = "vid_caption";
    private final String KEY_IMAGE ="image";

    private boolean parsingTextNode = false;
    private StringBuilder stringBuilder;

    private Course currCourse = null;
    private Unit currUnit = null;
    private Lesson currLesson = null;
    private LessonActivity currActivity = null;

    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<Lesson> lessons = new ArrayList<>();
    private final ArrayList<LessonActivity> activities = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        // TODO: logging
    }

    @Override
    public void startElement(String namespaceURI,
                         String localName,
                         String qName,
                         Attributes atts)
    throws SAXException {

        switch (localName) {
            case KEY_COURSE:
                String title, id;
                id = atts.getValue(0);
                title = atts.getValue(1);
                currCourse = new Course(id, title);
                break;

            case KEY_UNIT:
                title = atts.getValue(0);
                id = atts.getValue(1);
                currUnit = new Unit(title, id);
                break;

            case KEY_LESSON:
                String category;
                title = atts.getValue(0);
                id = atts.getValue(1);
                category = atts.getValue(2);
                currLesson = new Lesson(title, id, category);
                break;

            case KEY_SCREEN:
                currActivity = new LessonActivity();
                break;

            case KEY_SCREENID:
                parsingTextNode = true;
                stringBuilder = new StringBuilder();
                break;

            case KEY_VIDEO:
                parsingTextNode = true;
                stringBuilder = new StringBuilder();
                break;

            case KEY_VIDEO_CAPTION:
                parsingTextNode = true;
                stringBuilder = new StringBuilder();
                break;

            case KEY_IMAGE:
                parsingTextNode = true;
                stringBuilder = new StringBuilder();
                break;

            default:
                System.out.println("Unknown tag = ERRRORRR");
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI,
                         String localName,
                         String qName)
    throws SAXException {
        String text = "";
        switch (localName){
            case KEY_COURSE:
                if (!units.isEmpty()){
                    currCourse.setUnits(units);
                    units.clear();
                }
                courses.add(currCourse);
                break;

            case KEY_UNIT:
                if (!lessons.isEmpty()){
                    currUnit.setLessons(lessons);
                    lessons.clear();
                }
                units.add(currUnit);
                break;

            case KEY_LESSON:
                if (!activities.isEmpty()){
                    currLesson.setActivities(activities);
                    activities.clear();
                }
                lessons.add(currLesson);
                break;

            case KEY_SCREEN:
                activities.add(currActivity);
                break;

            case KEY_SCREENID:
                parsingTextNode = false;
                text = stringBuilder.toString();
                stringBuilder = null;
                currActivity.setScreenID(text);
                break;

            case KEY_VIDEO:
                parsingTextNode = false;
                text = stringBuilder.toString();
                stringBuilder = null;
                currActivity.setVideoUrl(text);
                break;

            case KEY_VIDEO_CAPTION:
                parsingTextNode = false;
                text = stringBuilder.toString();
                stringBuilder = null;
                currActivity.setVideoCaption(text);
                break;

            case KEY_IMAGE:
                parsingTextNode = false;
                text = stringBuilder.toString();
                stringBuilder = null;
                currActivity.setImagePath(text);
                break;
        }
    }

    @Override
    public void characters (char ch[], int start, int length)
        throws SAXException{

        if (parsingTextNode)
            stringBuilder.append(ch, start, length); // append when parsing nodes
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO: logging
        System.out.println("Done");
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }
}
