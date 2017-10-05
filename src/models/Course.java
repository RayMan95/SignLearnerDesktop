package models;

import java.util.ArrayList;

/**
 * Class representing the Course object in the SignLearner tool
 * Will only be useful with full curriculum
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @version 2017-09-27
 */
public class Course {
    private final String COURSE_ID, COURSE_TITLE;
    private final ArrayList<Unit> UNITS;
    private int INDEX = -1;
    
    /**
     * Only constructor, sets ID and Title
     * @param ID ID of this Course
     * @param title title of this Course
     */
    public Course(String ID, String title){
        UNITS = new ArrayList<>();
        COURSE_ID = ID;
        COURSE_TITLE = title;
    }
    
    /**
     * Has another Unit in UNITS
     * @return true if current index is before end
     */
    public boolean hasNext(){
        return (INDEX < (UNITS.size()-1));
    }
    
    /**
     * Get next Unit in UNITS
     * @return next Unit, or false if it does not exist
     */
    public Unit next(){
        if (!hasNext()) return null;
        ++INDEX;
        return UNITS.get(INDEX);
    }
    
    /**
     * Has a previous Unit in UNITS
     * @return true if current index is before start
     */
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }
    
    /**
     * Get previous Unit in UNITS
     * @return previous Unit, or false if it does not exist
     */
    public Unit previous(){
        if (!hasPrevious()) return null;
        --INDEX;
        return UNITS.get(INDEX);
    }
    
    /**
     * Adds units to UNITS
     * @param units to be added
     */
    public void addUnits(ArrayList<Unit> units){
        UNITS.addAll(units);
    }
    
    /**
     * Returns UNITS
     * @return UNITS
     */
    public ArrayList<Unit> getUnits(){
        return UNITS;
    }
    
    @Override
    public String toString(){
        String details = "Course: " + COURSE_ID + ")" + COURSE_TITLE;
        for (Unit u : UNITS){
            details += "\n";
            details += "  ";
            details += u.toString();
        }
        
        return details;
    }
}
