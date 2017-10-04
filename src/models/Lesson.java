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
<<<<<<< HEAD
    ArrayList<LessonActivity> lessonActivities;
    int index = 0;
=======
    private ArrayList<LessonActivity> ACTIVITIES;
    private LessonActivity currActivity; // ?
    private int INDEX = 0;
>>>>>>> xml_parser
    
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
        ACTIVITIES = new ArrayList<>();
        ACTIVITIES.addAll(activities);
    }
    
<<<<<<< HEAD
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
=======
    public LessonActivity getLessonActivity(String activityID){
        for (LessonActivity LA : ACTIVITIES){
            if (LA.getScreenID().equals(activityID)) return LA;
        }
        return null;
>>>>>>> xml_parser
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
    
    public boolean hasNext(){
        return (INDEX < (ACTIVITIES.size()-1));
    }
    
    public LessonActivity next(){
        if (!hasNext()) return null;
        ++INDEX;
        return ACTIVITIES.get(INDEX);
    }
    
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }
    
    public LessonActivity previous(){
        if (!hasPrevious()) return null;
        --INDEX;
        return ACTIVITIES.get(INDEX);
    }
    
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
        String details = "Lesson: " + ID + ") " + TITLE + " [" + CATEGORY + "]";
        if(ACTIVITIES != null){
            for (LessonActivity LA : ACTIVITIES){
                details += "\n";
                details += "      ";
                details += LA.toString();
            }
        }
        return details;
    }
}
