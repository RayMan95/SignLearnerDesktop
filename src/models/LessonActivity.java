package models;

/**
 * Class representing the LessonActivity object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @version 2017-09-05
 */
public class LessonActivity {
    private String SCREEN_ID, VIDEO_URL, VID_CAPTION, IMG_PATH;

    /**
     * Set this LessonActivity's screen ID
     * @param scrID screen ID
     */
    public void setScreenID(String scrID){
        SCREEN_ID = scrID;
    }

    /**
     * Set this LessonActivity's video URL
     * @param vidURL video URL
     */
    public void setVideoUrl(String vidURL){
        VIDEO_URL = vidURL;
    }

    /**
     * Set this LessonActivity's video caption
     * @param vidCap video caption
     */
    public void setVideoCaption(String vidCap){
        VID_CAPTION = vidCap;
    }

    /**
     * Set this LessonActivity's image path
     * @param imgPath image path
     */
    public void setImagePath(String imgPath){
        IMG_PATH = imgPath;
    }

    /**
     * Get this Lesson's title
     * @return SCREEN_ID
     */
    public String getScreenID(){
        return SCREEN_ID;
    }

    /**
     * Get this Lesson's title
     * @return VIDEO_URL
     */
    public String getVideoUrl(){
        return VIDEO_URL;
    }

    /**
     * Get this Lesson's title
     * @return VID_CAPTION
     */
    public String getVideoCaption(){
        return VID_CAPTION;
    }

   /**
     * Get this Lesson's title
     * @return IMG_PATH
     */
    public String getImagePath(){
        return IMG_PATH;
    }

    /**
     * Get this Lesson's attributes as a String array
     * @return atts
     */
    public String[] getAttributes(){
        String[] atts = {SCREEN_ID, VIDEO_URL, VID_CAPTION, IMG_PATH};
        return atts;
    }

    @Override
    public String toString(){
        String details = SCREEN_ID + ") " + VIDEO_URL + " " + VID_CAPTION;
        if(IMG_PATH != null) details += " " + IMG_PATH;
        else details += " [no image]";
        return details;
    }
}
