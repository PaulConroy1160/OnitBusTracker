package com.example.paulconroy.onit.model;

/**
 * Created by paulconroy on 16/12/2015.
 */
public class BusData {

    private int id;
    private String duetime;
    private String destination;
    private String route;

    public BusData() {
        id = 0;
        duetime = "";
        destination = "";
        route = "";
    }

    public BusData(int id, String m, String d, String r) {
        id = id;
        duetime = m;
        destination = d;
        route = r;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String d) {
        this.destination = d;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String r) {
        this.route = r;
    }
}
