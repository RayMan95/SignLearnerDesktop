/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;
import models.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing functionality for building course structures by parsing XML
 * files created by the Authoring Tool
 * @author Rayaan Fakier
 * 
 */
public class XMLParser extends DefaultHandler {
    
    private final String KEY_COURSE = "course";
    private final String UNIT_TITLE = "unitTitle";
    private final String UNIT_ID = "unitID";
    private final String KEY_UNIT = "unit";
    private final String KEY_LESSON ="lesson";
    private final String KEY_SCREEN = "screen";
    private final String KEY_SCREENID = "screenID";
    private final String KEY_VIDEO = "video";
    private final String KEY_VIDEO_CAPTION = "vid_caption";
    private final String KEY_IMAGE ="image";
//    private final String LOGTAG = "COURSE_PARSER";
    private boolean parsingTextNode = false;
    private StringBuilder STR_BUILDER;
    
    private final static String FILE_NAME = "../icdl/xml/e learner self study.xml"; // start pos: SignLearnerDesktop/dist

    private String currTag = null;
    private Lesson currLesson = null;
    private LessonActivity currActivity = null;
    
    public ArrayList<Lesson> lessons = new ArrayList<>();
    public ArrayList<LessonActivity> activities = new ArrayList<>();
        
    private Stack<String> tags;
    
    @Override
    public void startDocument() throws SAXException {
        lessons = new ArrayList<>();
        tags = new Stack<>();
    }
    
    @Override
    public void startElement(String namespaceURI,
                         String localName,
                         String qName, 
                         Attributes atts)
    throws SAXException {

        String key = localName;
    
        switch (localName) {
            case KEY_COURSE:
//                System.out.println(KEY_COURSE); // skip for now
                break;
            case KEY_UNIT:
//                System.out.println(KEY_UNIT); // skip for now
                break;
            case KEY_LESSON:
                String title, id, category;
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
                STR_BUILDER = new StringBuilder();
                break;
            case KEY_VIDEO:
                parsingTextNode = true;
                STR_BUILDER = new StringBuilder();
                break;
            case KEY_VIDEO_CAPTION:
                parsingTextNode = true;
                STR_BUILDER = new StringBuilder();
                break;
            case KEY_IMAGE:
                parsingTextNode = true;
                STR_BUILDER = new StringBuilder();
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
            case KEY_LESSON:
                if (!activities.isEmpty())
                    currLesson.setActivities(activities);
                lessons.add(currLesson);
                break;
                
            case KEY_SCREEN:
                activities.add(currActivity);
                break;
                
            case KEY_SCREENID:
                parsingTextNode = false;
                text = STR_BUILDER.toString();
                STR_BUILDER = null;
                currActivity.setScreenID(text);
                break;
                
            case KEY_VIDEO:
                parsingTextNode = false;
                text = STR_BUILDER.toString();
                STR_BUILDER = null;
                currActivity.setVideoUrl(text);
                break;
                
            case KEY_VIDEO_CAPTION:
                parsingTextNode = false;
                text = STR_BUILDER.toString();
                STR_BUILDER = null;
                currActivity.setVideoCaption(text);
                break;
                
            case KEY_IMAGE:
                parsingTextNode = false;
                text = STR_BUILDER.toString();
                STR_BUILDER = null;
                currActivity.setImagePath(text);
                break;
        }
    
    
    }
    
    @Override
    public void characters (char ch[], int start, int length)
        throws SAXException{
        
        if (parsingTextNode)
            STR_BUILDER.append(ch, start, length); // append when parsing nodes
    
    }

    @Override
    public void endDocument() throws SAXException {
//        Set e = tags.keySet();
//        for (Iterator it = e.iterator(); it.hasNext(); it.next()){
//            String tag = (String)it.toString();
//            int count = ((Integer)tags.get(tag));
//            System.out.println("Local Name \"" + tag + "\" occurs " 
//                               + count + " times");
//        }  
        System.out.println("Done");
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

