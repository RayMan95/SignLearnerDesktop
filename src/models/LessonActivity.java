package models;

/**
 * Class representing the LessonActivity object in the SignLearner tool
 * @author Rayaan Fakier
 * @date 2017-09-05
 */
public class LessonActivity {
    private String SCREEN_ID, VIDEO_URL, VID_CAPTION, IMG_PATH;
    
    // getters and setters
    
    public void setScreenID(String scrID){
        SCREEN_ID = scrID;
    }
    
    public void setVideoUrl(String vidURL){
        VIDEO_URL = vidURL;
    }
    
    public void setVideoCaption(String vidCap){
        VID_CAPTION = vidCap;
    }
    
    public void setImagePath(String imgPath){
        IMG_PATH = imgPath;
    }
    
    public String getScreenID(){
        return SCREEN_ID;
    }
    
    public String getVideoUrl(){
        return VIDEO_URL;
    }
    
    public String getVideoCaption(){
        return VID_CAPTION;
    }
    
    public String getImagePath(){
        return IMG_PATH;
    }
    
    public String[] getAttributes(){
        String[] atts = {SCREEN_ID, VIDEO_URL, VID_CAPTION, IMG_PATH};
        return atts;
    }
    
    // utility methods
    
    @Override
    public String toString(){
        String details = SCREEN_ID + ") " + VIDEO_URL + " " + VID_CAPTION;
        if(IMG_PATH != null) details += " " + IMG_PATH;
        else details += " [no image]";
        return details;
    }
}
