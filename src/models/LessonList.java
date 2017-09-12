/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 * Class representing the LessonList object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class LessonList {
    private ArrayList<Lesson> allLessons;
    private final String UNIT_TITLE, UNIT_ID; // course?
    
    public LessonList(String unit, String uid){ // course?
        UNIT_TITLE = unit;
        UNIT_ID = uid;
    }
    
    public void setLessons(ArrayList<Lesson> lessons){
        allLessons = new ArrayList<>();
        for(Lesson l : lessons){
            allLessons.add(l);
        }
    }
    
    public Lesson getLesson(String lesson_title){
        for (Lesson L : allLessons){
            if (L.getTitle().equals(lesson_title)) return L;
        }
        return null;
    }
    
    @Override
    public String toString(){
        String details = UNIT_ID + ")" + UNIT_TITLE;
        if(allLessons != null){
            for (Lesson L : allLessons){
                details += "\n";
                details += "  ";
                details += L.toString();
            }
        }
        return details;
        
    }
}
