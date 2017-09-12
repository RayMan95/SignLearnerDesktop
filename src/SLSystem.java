
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import models.LessonList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import xmlparser.XMLParser;

/**
 * Class representing controller of the application
 * @author  Rayaan Fakier
 * @date 2017-09-05
 */
public class SLSystem {
    
    private final static String FILE_NAME = "../icdl/xml/e learner self study.xml"; // start pos: SignLearnerDesktop/dist
    private ArrayList<LessonList> UNITS = new ArrayList<>();
    
    public SLSystem(){
        
        try{
            UNITS = this.build();
        }
        catch(IOException | SAXException | ParserConfigurationException e){
            // ?
        }
    }
    
    private ArrayList<LessonList> build() throws IOException, SAXException, ParserConfigurationException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        
        XMLReader xmlReader = saxParser.getXMLReader();
        XMLParser xmlp = new XMLParser();
        xmlReader.setContentHandler(xmlp);
        xmlReader.parse(convertToFileURL());
        
        return xmlp.getUnits();
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
    
    public ArrayList<LessonList> getUnits(){
        return UNITS;
    }
}
