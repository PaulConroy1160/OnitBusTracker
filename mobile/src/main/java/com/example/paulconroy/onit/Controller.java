package com.example.paulconroy.onit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by paulconroy on 17/12/2015.
 */
public class Controller {

    Context mContext;
    Calendar cal = Calendar.getInstance();
    AlarmManager alarm;
    PendingIntent pintent;


    public Controller(Context context) {
        mContext = context;
    }

    public void alarmExample() {
        Intent intent = new Intent(mContext, ServiceGB.class);
        pintent = PendingIntent.getService(mContext, 0, intent, 0);
        alarm = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
    }

    public void stopAlarm() {
        Log.d("testing", "ending alarm");
        alarm.cancel(pintent);

        // Log.d("testing",mContext.toString());

        //Toast.makeText(mContext,"YOUR BUS IS HERE",Toast.LENGTH_LONG).show();

    }


}
