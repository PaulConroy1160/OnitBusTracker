package com.example.paulconroy.onit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.paulconroy.onit.model.Model;

/**
 * Created by paulconroy on 17/12/2015.
 */
public class ServiceGB extends Service {
    CheckBusUpdateAsync client;
    Model model = Model.getInstance();
    String route;
    Boolean busAlert;
    PendingIntent pintent;
    AlarmManager alarm;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d("Testing", "Service got created");
        Toast.makeText(this, "ServiceClass.onCreate()", Toast.LENGTH_LONG).show();


        route = model.getRoute();


    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        busAlert = model.getBusAlert();
        if (!busAlert) {
            Toast.makeText(this, "ServiceClass.onStart()", Toast.LENGTH_LONG).show();
            Log.d("Testing", "Service got started");

            client = new CheckBusUpdateAsync(this);
            client.execute("http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=" + route + "&format=json");

            stopSelf();
        } else {
            Log.d("testing", "alarm has now stopped");
            Intent intentStop = new Intent(this, ServiceGB.class);
            intent.putExtra("route", route);
            pintent = PendingIntent.getService(this, 0, intentStop, PendingIntent.FLAG_UPDATE_CURRENT);
            alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarm.cancel(pintent);

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }

        return super.onStartCommand(intent, flags, startId);
    }


}








