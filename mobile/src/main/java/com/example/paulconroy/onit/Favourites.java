package com.example.paulconroy.onit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paulconroy.onit.Database.DatabaseHandler;
import com.example.paulconroy.onit.model.Model;
import com.example.paulconroy.onit.model.StopSave;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by paulconroy on 16/12/2015.
 */
public class Favourites extends Activity {


    public List<StopSave> busList;
    DatabaseHandler db;
    Model m;
    ListView busListView;
    String busNumber = "null";
    Controller mController;
    PendingIntent pintent;
    AlarmManager alarm;
    Calendar cal = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_list_fav);

        m = Model.getInstance();
        db = new DatabaseHandler(this);

        mController = new Controller(this);


        busNumber = getIntent().getStringExtra("number");

        m.setRoute(busNumber);


        busList = new ArrayList<StopSave>();

        //REMOVE WHEN TESTING
        busList = db.getAllSaves();

        //busList = fakeTest();

        busListView = (ListView) findViewById(R.id.busListFavView);

        if (!busList.isEmpty()) {
            changeAdapter(busList);
            //loadAnimation();
        } else {
            Toast.makeText(this, "No buses available", Toast.LENGTH_LONG).show();
        }

    }

    private void changeAdapter(List<StopSave> busList) {
        ListAdapter adapter = new SaveListAdapter(this,
                R.id.busListFavView, busList);
        busListView.setAdapter(adapter);
    }


    public void loadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        busListView.startAnimation(animation);
    }


}
