/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class representing the Lesson object in the SignLearner tool
 * @author Rayaan Fakier
 * @date 2017-09-05
 */
public class Lesson {
    private final String TITLE, ID, CATEGORY;
    ArrayList<LessonActivity> lessonActivities;
    int index = 0;
    
    /**
     * Only constructor
     * @param title Title of lesson
     * @param id ID of lesson
     * @param cat Category of lesson
     */
    public Lesson(String title, String id, String cat){
        TITLE = title;
        ID = id;
        CATEGORY = cat;
    }
    
    public boolean hasActivities(){
        return lessonActivities != null || lessonActivities.size() > 0;
    }
    
    public boolean hasNext(){
        if (!hasActivities()) return false;
        return index < (lessonActivities.size()-1); 
    }
    
    public boolean hasPrevious(){
        if (!hasActivities()) return false;
        return index > 0;
    }
    
    public LessonActivity getNext(){
        LessonActivity activity = null;
        
        if (hasNext()){ 
            ++index;
            activity = lessonActivities.get(index);
        }
        return activity;
    }
    
    public LessonActivity getPrevious(){
        LessonActivity activity = null;
        
        if (hasPrevious()){ 
            --index;
            activity = lessonActivities.get(index);
        }
        return activity;
    }
    
    // Getters and setters
    
    public void setActivities(ArrayList<LessonActivity> activities){
        lessonActivities = new ArrayList<>();
        for (LessonActivity la : activities){
            lessonActivities.add(la);
        }
    }
    
    public LessonActivity getActivity(String activityID){
        LessonActivity activity = null;
        int i = 0;
        for (LessonActivity la : lessonActivities){
            
            if (la.getScreenID().equals(activityID)){
                activity = la;
                index = i;
            }
            ++i;
        }
        
        return activity;
    }
    
    public LessonActivity getActivity(int i){
        if (!hasActivities()) return null;
        if (i < 0 || i >= lessonActivities.size()){
            index = i;
            return null;
        }
        
        return lessonActivities.get(index);
    }
    
    public String getTitle(){
        return TITLE;
    }
    
    public String getID(){
        return ID;
    }
    
    public String getCategory(){
        return CATEGORY;
    }
    
    // Utility methods
    
    @Override
    public boolean equals(Object other){
        if (!(other instanceof Lesson)){
            return false;
        }
        Lesson l = (Lesson)other;
        if(!l.CATEGORY.equals(CATEGORY)) return false;
        else if (!l.ID.equals(ID)) return false;
        else if (!l.TITLE.equals(TITLE)) return false;
            
        return true;
    }
    
    @Override
    public String toString(){
        String details = ID + ") " + TITLE + " [" + CATEGORY + "]";
        if(lessonActivities != null){
            for (LessonActivity LA : lessonActivities){
                details += "\n";
                details += "    ";
                details += LA.toString();
            }
        }
        return details;
    }
}
