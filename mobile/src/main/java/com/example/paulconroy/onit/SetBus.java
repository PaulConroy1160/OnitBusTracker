package com.example.paulconroy.onit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.paulconroy.onit.model.Model;

import java.util.Calendar;

/**
 * Created by paulconroy on 17/12/2015.
 */
public class SetBus extends Activity {

    Button endAlarm;
    PendingIntent pintent;
    AlarmManager alarm;
    Model model = Model.getInstance();
    Controller mController;
    Boolean setAlarm = true;
    String route;
    Model m = Model.getInstance();

    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        endAlarm = (Button) findViewById(R.id.endAlarm);

        route = getIntent().getStringExtra("busNumber");
        mController = new Controller(this);

        Log.d("route?", "" + route);
        model.setRoute(route);


    }

    public void alarmExample() {
//        model.setBusAlert(false);
//        Intent intent = new Intent(this, ServiceGB.class);
//        intent.putExtra("route", route);
//        pintent = PendingIntent.getService(this, 0, intent, 0);
//        alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1 * 1000, pintent);
    }

    public void stopAlarm() {
        Log.d("testing", "ALARM OVER");
        alarm.cancel(pintent);
        setAlarm = true;
    }
}
