/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class representing the LessonActivity object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class LessonActivity {
    private String screenID, videoUrl, videoCaption, imagePath;
    
    public void setScreenID(String scrID){
        screenID = scrID;
    }
    
    public void setVideoUrl(String vidURL){
        videoUrl = vidURL;
    }
    
    public void setVideoCaption(String vidCap){
        videoCaption = vidCap;
    }
    
    public void setImagePath(String imgPath){
        imagePath = imgPath;
    }
    
    public String getScreenID(){
        return screenID;
    }
    
    public String getVideoUrl(){
        return videoUrl;
    }
    
    public String getVideoCaption(){
        return videoCaption;
    }
    
    public String getImagePath(){
        return imagePath;
    }
    
    public String[] getAttributes(){
        String[] atts = {screenID, videoUrl, videoCaption, imagePath};
        return atts;
    }
    
    @Override
    public String toString(){
        String details = screenID + ") " + videoUrl + " " + videoCaption;
        if(imagePath != null) details += " " + imagePath;
        else details += " [no image]";
        return details;
    }
}
