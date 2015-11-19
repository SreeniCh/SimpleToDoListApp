package com.schalamcharla.simpletodolistapp.util;

//import java.util.Date;
import com.orm.SugarRecord;

/**
 * Created by schalamcharla on 11/18/15.
 */
public class Item extends SugarRecord<Item> {
    long id;
    String name;
    //String _taskDetails;
    //Date _date;
    //type (enum: {notStarted, inProgress, Completed}
    //priority (enum: low/medium/high)

    public Item() {}

    /*public Item(int id) {
        this.id = id;
    }*/

    //public Task(int id, String taskName) {
    public Item(String name) {
        //this.id = id;
        this.name = name;
    }
    public Item(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public long getID(){ return this.id; }
    public String getName() { return this.name; }

    // Setters
    public void setID(int id) { this.id = id; }
    public void setName(String name){
        this.name = name;
    }
}
