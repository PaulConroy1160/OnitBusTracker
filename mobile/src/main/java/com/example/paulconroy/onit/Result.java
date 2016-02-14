package com.example.paulconroy.onit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulconroy.onit.Database.DatabaseHandler;
import com.example.paulconroy.onit.model.BusData;
import com.example.paulconroy.onit.model.Model;
import com.example.paulconroy.onit.model.StopSave;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by paulconroy on 16/12/2015.
 */
public class Result extends Activity {

    public List<BusData> busList;
    TextView busNumberText;
    Model m;
    ListView busListView;
    ImageButton set;
    String busNumber = "null";
    Controller mController;
    PendingIntent pintent;
    AlarmManager alarm;
    Calendar cal = Calendar.getInstance();
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_list);

        m = Model.getInstance();
        db = new DatabaseHandler(this);

        mController = new Controller(this);

        busNumberText = (TextView) findViewById(R.id.busNumber);
        set = (ImageButton) findViewById(R.id.setAlarm);

        busNumber = getIntent().getStringExtra("number");

        m.setRoute(busNumber);
        busNumberText.setText(busNumber);

        busList = new ArrayList<BusData>();

        //REMOVE WHEN TESTING
        busList = m.getBusList();

        //busList = fakeTest();

        busListView = (ListView) findViewById(R.id.busListView);

        if (!busList.isEmpty()) {
            changeAdapter(busList);
            //loadAnimation();
        } else {
            Toast.makeText(this, "No buses available", Toast.LENGTH_LONG).show();
        }

    }

    private void changeAdapter(List<BusData> busList) {
        ListAdapter adapter = new BusListAdapter(this,
                R.id.busListView, busList);
        busListView.setAdapter(adapter);
    }

    public void setAlarm(View v) {
        m.setBusAlert(false);
        Intent intent = new Intent(this, ServiceGB.class);
        intent.putExtra("route", busNumber);
        pintent = PendingIntent.getService(this, 0, intent, 0);
        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1 * 1000, pintent);


    }

    public void saveStop(View v) {
        StopSave save = new StopSave();
        String location = m.getLocation();

        save.setNumber(busNumber);
        save.setLocation(location);
        db.addSave(save);
    }

    public List<BusData> fakeTest() {
        BusData busData = new BusData(1, "something", "Bray", "184");
        BusData busData1 = new BusData(2, "more", "Cork", "194");
        BusData busData2 = new BusData(3, "Hi", "Bray", "123");
        BusData busData3 = new BusData(4, "again", "Shankill", "184");

        busList.add(busData);
        busList.add(busData1);
        busList.add(busData2);
        busList.add(busData3);

        return busList;
    }

    public void loadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        busListView.startAnimation(animation);
    }


}
