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
    ArrayList<LessonActivity> lessonactivities;
    LessonActivity currActivity;
    
    public Lesson(String title, String id, String cat){
        TITLE = title;
        ID = id;
        CATEGORY = cat;
    }
    
    public String[] getAllActivityAttributes(){
        return null;
    }
    
    public String getID(){
        return null;
    }
    
    public String getTitle(){
        return null;
    }
    
    public String getCategory(){
        return null;
    }
    
    @Override
    public String toString(){
        return null;
    }
    
}
