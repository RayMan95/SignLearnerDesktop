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
    
//    private final String KEY_COURSE = "course";
//    private final String UNIT_TITLE = "unitTitle";
//    private final String UNIT_ID = "unitID";
//    private final String KEY_UNIT = "unit";
//    private final String KEY_LESSON ="lesson";
//    private final String KEY_SCREEN = "screen";
//    private final String KEY_SCREENID = "screenID";
//    private final String KEY_IMAGE ="image";
//    private final String KEY_VIDEO = "video";
//    private final String KEY_VIDEO_CAPTION = "vid_caption";
//    private final String LOGTAG = "COURSE_PARSER";
    
    private final static String FILE_NAME = "../icdl/xml/e learner self study.xml"; // start pos: SignLearnerDesktop/dist

    private String currTag = null;
    private Lesson currLesson = null;
    private LessonActivity currActivity = null;
    
    public ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    public ArrayList<LessonActivity> screenss = new ArrayList<LessonActivity>();
    
    /*
    * Constructor
    * 
    */
    public ArrayList<Lesson> parse (){
        lessons = new ArrayList<>();
        try{
            File file = new File(FILE_NAME);
            if (!file.exists()){
                System.out.println("File name: " + FILE_NAME);
                throw new FileNotFoundException();                
            }
            System.out.println("Found!");
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            
            System.exit(1);
        }
        
        return lessons;
    }
    
    private HashMap tags;
    
    @Override
    public void startDocument() throws SAXException {
        lessons = new ArrayList<>();
        tags = new HashMap();
    }
    
    @Override
    public void startElement(String namespaceURI,
                         String localName,
                         String qName, 
                         Attributes atts)
    throws SAXException {

    String key = localName;
    Object value = tags.get(key);

    if (value == null) {
        tags.put(key, 1);
    } 
    else {
        int count = ((Integer)value);
        count++;
        tags.put(key, count);
    }
}

    @Override
    public void endDocument() throws SAXException {
        Set e = tags.keySet();
        for (Iterator it = e.iterator(); it.hasNext(); it.next()){
            String tag = (String)it.toString();
            int count = ((Integer)tags.get(tag));
            System.out.println("Local Name \"" + tag + "\" occurs " 
                               + count + " times");
        }    
    }
 
    public  static String convertToFileURL(String filename) {
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
