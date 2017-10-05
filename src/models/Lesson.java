package models;

import java.util.ArrayList;

/**
 * Class representing the Lesson object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @version 2017-09-05
 */
public class Lesson {
    private final String TITLE, ID, CATEGORY;

    private final ArrayList<LessonActivity> ACTIVITIES;
    private LessonActivity currActivity; // ?
    private int INDEX = 0;

    /**
     * Only constructor
     * @param title Title of lesson
     * @param id ID of lesson
     * @param cat Category of lesson
     */
    public Lesson(String title, String id, String cat){
        ACTIVITIES = new ArrayList<>();
        TITLE = title;
        ID = id;
        CATEGORY = cat;
    }

    /**
     * Add LessonActivitys to ACTIVITIES
     * @param activities LessonActivitys to be added
     */   
    public void addActivities(ArrayList<LessonActivity> activities){
        ACTIVITIES.addAll(activities);
    }

    /**
     * Get this Lesson's title
     * @return TITLE
     */
    public String getTitle(){
        return TITLE;
    }

    /**
     * Get this Lesson's ID
     * @return ID
     */
    public String getID(){
        return ID;
    }

    /**
     * Get this Lesson's category
     * @return CATEGORY
     */
    public String getCategory(){
        return CATEGORY;
    }
    
    /**
     * Has another LessonActivity in ACTIVITIES
     * @return true if current index is before end
     */
    public boolean hasNext(){
        return (INDEX < (ACTIVITIES.size()-1));
    }

    /**
     * Get next LessonActivity in ACTIVITIES
     * @return next LessonActivity, or false if it does not exist
     */
    public LessonActivity next(){
        if (!hasNext()) return null;
        ++INDEX;
        return ACTIVITIES.get(INDEX);
    }

    /**
     * Has a previous LessonActivity in ACTIVITIES
     * @return true if current index is before start
     */
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }

    /**
     * Get previous LessonActivity in ACTIVITIES
     * @return previous LessonActivity, or false if it does not exist
     */
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
        if(!ACTIVITIES.isEmpty()){
            for (LessonActivity LA : ACTIVITIES){
                details += "\n";
                details += "      ";
                details += LA.toString();
            }
        }
        return details;
    }
}
