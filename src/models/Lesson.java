/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 * Class representing the Lesson object in the SignLearner tool
 * @author  Luveshen Pillay, Christopher Mudongo, Rayaan Fakier
 * @date 2017-09-05
 */
public class Lesson {
    private final String TITLE, ID, CATEGORY;
    ArrayList<LessonActivity> lessonActivities;
    LessonActivity currActivity;
    
    public Lesson(String title, String id, String cat){
        TITLE = title;
        ID = id;
        CATEGORY = cat;
    }
    
    public void setActivities(ArrayList<LessonActivity> activities){
        lessonActivities = activities;
    }
    
    public String[] getAllActivityAttributes(){
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
    
    @Override
    public String toString(){
        return TITLE + " " + ID + " " + CATEGORY;
    }
    
}
