package models;

import java.util.ArrayList;

/**
 * Class representing the Unit object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @version 2017-09-05
 */
public class Unit {
    private final String UNIT_TITLE, UNIT_ID;
    private final ArrayList<Lesson> LESSONS;
    private int INDEX = -1;
    
    /**
     * Only constructor, sets Unit title and ID
     * @param unitTitle title of this Unit
     * @param unitID ID of this Unit
     */
    public Unit(String unitTitle, String unitID){
        LESSONS = new ArrayList<>();
        UNIT_TITLE = unitTitle;
        UNIT_ID = unitID;
    }
    
    /**
     * Add Lessons to LESSONS
     * @param lessons Lessons to be added
     */    
    public void addLessons(ArrayList<Lesson> lessons){
        LESSONS.addAll(lessons);
    }
    
    /**
     * Get this Unit's title
     * @return UNIT_TITLE
     */
    public String getTitle(){
        return UNIT_TITLE;
    }
    
    /**
     * Get this Unit's ID
     * @return UNIT_ID
     */
    public String getID(){
        return UNIT_ID;
    }
    
    /**
     * Has another Lesson in LESSONS
     * @return true if current index is before end
     */
    public boolean hasNext(){
        
        return (INDEX < (LESSONS.size()-1));
    }
    
    /**
     * Get next Lesson in LESSONS
     * @return next Lesson, or false if it does not exist
     */
    public Lesson next(){
        if (!hasNext()) return null;
        ++INDEX;
        return LESSONS.get(INDEX);
    }
    
    /**
     * Has a previous Lessons in LESSONS
     * @return true if current index is before start
     */
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }
    
    /**
     * Get previous Lessons in LESSONS
     * @return previous Lesson, or false if it does not exist
     */
    public Lesson previous(){
        if (!hasPrevious()) return null;
        --INDEX;
        return LESSONS.get(INDEX);
    }
    
    @Override
    public String toString(){
        String details = "Unit: " + UNIT_ID + ")" + UNIT_TITLE;
        for (Lesson L : LESSONS){
            details += "\n";
            details += "    ";
            details += L.toString();
        }
        return details;
    }
}
