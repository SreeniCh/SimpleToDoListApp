package com.schalamcharla.simpletodolistapp.util;

//import java.util.Date;
import com.orm.SugarRecord;

/**
 * Created by schalamcharla on 11/18/15.
 */
public class Item extends SugarRecord<Item> {
    //int id;
    String name;
    //String _taskDetails;
    //Date _date;
    //type (enum: {notStarted, inProgress, Completed}
    //priority (enum: low/medium/high)

    public Item() {}

    //public Task(int id, String taskName) {
    public Item(String name) {
        //this.id = id;
        this.name = name;
    }

    /*public int getID(){
        return this.id;
    }
    public void setID(int id){
        this.id = id;
    }*/

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
