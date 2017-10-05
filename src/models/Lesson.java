package models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class representing the Lesson object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class Lesson {
    private final String TITLE, ID, CATEGORY;

    private ArrayList<LessonActivity> ACTIVITIES;
    private LessonActivity currActivity; // ?
    private int INDEX = 0;

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

    // Getters and setters

    public void setActivities(ArrayList<LessonActivity> activities){
        ACTIVITIES = new ArrayList<>();
        ACTIVITIES.addAll(activities);
    }

    public LessonActivity getLessonActivity(String activityID){
        for (LessonActivity LA : ACTIVITIES){
            if (LA.getScreenID().equals(activityID)) return LA;
        }
        return null;
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
