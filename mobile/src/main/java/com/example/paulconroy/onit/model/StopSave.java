package com.example.paulconroy.onit.model;

/**
 * Created by paulconroy on 20/12/2015.
 */
public class StopSave {

    private int id;
    private String number;
    private String location;

    public StopSave() {
        this.id = 0;
        this.number = "";
        this.location = "";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
