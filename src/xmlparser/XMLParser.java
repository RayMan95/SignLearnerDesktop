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

//    public ArrayList<Lesson> parse(Context context)  {
//
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser parser = factory.newPullParser();
//
//            File file = new File(Environment.getExternalStorageDirectory(), "/SignSupport/icdl/xml/e learner self study.xml");
///*Check if the file exists*/
//            if (!file.exists()) {
//                Log.d(LOGTAG, file + " does not exist");
//            } else {
//                /*Open an inputstream to read from the file*/
//                InputStream stream = new FileInputStream(file.getAbsolutePath());
//                parser.setInput(stream, null);
//
//                int eventType = parser.getEventType();
//
//
//                while(eventType != XmlPullParser.END_DOCUMENT){
//                    /*Get the XML tag name and switch between the tags parsing the XML*/
//                    String tagname = parser.getName();
//
//
//                    switch (eventType){
//                        case XmlPullParser.START_TAG:
//                            if(tagname.equalsIgnoreCase(KEY_COURSE)){
//                                Log.d(LOGTAG, "new course");
//                            }else if (tagname.equalsIgnoreCase(KEY_UNIT)){
//
//                                String unit_id = parser.getAttributeValue(null,"unit_id");
//                                String unit_title = parser.getAttributeValue(null, "unit_title");
//                                Log.d(LOGTAG, "new unit title= " + unit_title+ " with id= "+unit_id);
//
//                            }else if (tagname.equalsIgnoreCase(KEY_LESSON)){
//
//                                String title = parser.getAttributeValue(null, "lesson_title");
//                                String id = parser.getAttributeValue(null, "lesson_id");
//                                String lesson_type = parser.getAttributeValue(null, "lesson_type");
//                                Log.d(LOGTAG, "lesson title="+title+" lesson id="+id+" lesson type="+lesson_type);
//
//                                currLesson = new Lesson();
//                                currLesson.setCategory(lesson_type);
//                                currLesson.setId(id);
//                                currLesson.setTitle(title);
////                                currLesson.setScreens(screenss);
//
//                                lessons.add(currLesson);
//                                if(screenss.isEmpty()){
//                                    Log.d(LOGTAG, "screens list is empty");
//                                }else {
//
//                                    Log.d(LOGTAG, "Screens list has "+ Integer.toString(screenss.size()) );
////                                    screenss.clear();
//                                }
//
//                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){
//
//                                Log.d(LOGTAG, "New Screen object");
//                                currScreen = new Screen(); // create new screen object once you get a screen tag
////
//                                screenss.add(currScreen); // add current screen to the list of screens
//                            }
//                            break;
//                        case XmlPullParser.TEXT:
//                            currTag = parser.getText();
//                            break;
//
//                        case XmlPullParser.END_TAG:
//                            if(tagname.equalsIgnoreCase(KEY_COURSE)){
//                                Log.d(LOGTAG, "End of course reached");
//                            }else if(tagname.equalsIgnoreCase(KEY_UNIT)){
//                                Log.d(LOGTAG, "end of unit");
//                            }else if(tagname.equalsIgnoreCase(KEY_LESSON)){
//
//                                    if(screenss.isEmpty())
//                                    {
//                                        Log.d(LOGTAG, "No screens for this lesson ");
//                                    }else{
//                                        currLesson.setScreens(screenss); // Set the current lesson to the list of screens
//                                        Log.d(LOGTAG, "list of screens added for Lesson: " + currLesson.getTitle()+ " with size "+ Integer.toString(screenss.size()));
//                                        Log.d(LOGTAG, "End of lesson");
////
//                                    }
////                                screenss.clear();
////                                lessons.add(currLesson);
//                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){
////                                screenss.add(currScreen);
//
//
//                            } else if(tagname.equalsIgnoreCase(KEY_IMAGE)){
//                                /*Store the image path*/
//                                currScreen.setImagePath(currTag);
//                            } else if (tagname.equalsIgnoreCase(KEY_SCREENID)){
//                                /*Store the Screen ID*/
//                                currScreen.setScreenID(currTag);
//                            } else if(tagname.equalsIgnoreCase(KEY_VIDEO)){
//                                /*Store the videoURL*/
//                                currScreen.setVideoURL(currTag);
//                            }else if (tagname.equalsIgnoreCase(KEY_VIDEO_CAPTION)){
//                                /*Store the video caption*/
//                                currScreen.setVidCaption(currTag);
//                            }
//                            break;
//                        default:
//                            break;
//
//                    }
//                    eventType = parser.next();
//                }
//
//            }
//        }catch (Resources.NotFoundException e){
//            e.getMessage();
//        }catch (XmlPullParserException e){
//            e.getMessage();
//        }catch (IOException e){
//            e.getMessage();
//        }
//
//        return lessons;
//    }
//}
