package models;

import java.util.ArrayList;

/**
 * Class representing the Unit object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class Unit {
    private final String UNIT_TITLE, UNIT_ID;
    private final ArrayList<Lesson> LESSONS;
    private int INDEX = -1;
    
    public Unit(String unit, String uid){
        LESSONS = new ArrayList<>();
        UNIT_TITLE = unit;
        UNIT_ID = uid;
    }
    
    // getters and setters
    
    public void setLessons(ArrayList<Lesson> lessons){
        LESSONS.addAll(lessons);
    }
    
    public Lesson getLesson(String lessonTitle, String lessonID){
        for (Lesson L : LESSONS){
            if (L.getTitle().equals(lessonTitle) && L.getID().equals(lessonID)) 
                return L;
        }
        return null;
    }
    
    public String getTitle(){
        return UNIT_TITLE;
    }
    
    public String getID(){
        return UNIT_ID;
    }
    
    // utility methods
    
    public boolean hasNext(){
        
        return (INDEX < (LESSONS.size()-1));
    }
    
    public Lesson next(){
        if (!hasNext()) return null;
        ++INDEX;
        return LESSONS.get(INDEX);
    }
    
    public boolean hasPrevious(){
        return (INDEX  > 0);
    }
    
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
