package models;

import java.util.ArrayList;

/**
 * Class representing the Course object in the SignLearner tool
 * Will only be useful with full curriculum
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-27
 */
public class Course {
    private final String COURSE_ID, COURSE_TITLE;
    private ArrayList<Unit> UNITS;
    private int INDEX = 0;
    
    public Course(String ID, String title){
        COURSE_ID = ID;
        COURSE_TITLE = title;
    }
    
    public boolean hasNext(){
        return (INDEX < (UNITS.size()-1));
    }
    
    public Unit next(){
        if (!hasNext()) return null;
        ++INDEX;
        return UNITS.get(INDEX);
    }
    
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }
    
    public Unit previous(){
        if (!hasPrevious()) return null;
        --INDEX;
        return UNITS.get(INDEX);
    }
    
    public void setUnits(ArrayList<Unit> units){
        UNITS = new ArrayList<>();
        UNITS.addAll(units);
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
