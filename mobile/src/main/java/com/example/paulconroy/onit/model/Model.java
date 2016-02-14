package com.example.paulconroy.onit.model;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulconroy on 16/12/2015.
 */
public class Model {

    // create a static instance of com.example.paulconroy.onit.model - set to null
    private static Model instance = null;
    private List<BusData> busList;
    private BusData bus;
    private String busNumber;
    private String route;
    private AlarmManager alarm;
    private PendingIntent pintent;
    private String location;
    private Boolean alert = false;
    private int i = 0;
    private Model() {
        this.busList = new ArrayList<BusData>();
    }

    // synchronized locks method
    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void addBus(BusData bus) {

        this.busList.add(bus);
    }

    public List<BusData> getBusList() {

        return this.busList;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String r) {
        this.route = r;
    }

    public void addBusNumber(String num) {
        this.busNumber = num;
    }

    public String getBusNumber() {
        return this.busNumber;
    }

    public void emptyBusList() {
        if (!busList.isEmpty()) {
            this.busList.clear();
        }

    }

    public PendingIntent getPendingIntent() {
        return this.pintent;
    }

    public void setAlarmAndPintent(AlarmManager a, PendingIntent p) {
        this.alarm = a;
        this.pintent = p;
    }

    public void stopAlarm() {
        alarm.cancel(pintent);
    }

    public Boolean getBusAlert() {
        return this.alert;
    }

    public void setBusAlert(Boolean a) {
        this.alert = a;
    }

    public String getLocation() {
        return this.location;
    }

    public void addLocation(String l) {
        this.location = l;
    }

    public void settest() {
        i++;
    }

    public int getTest() {
        return this.i;
    }

}
