package com.example.paulconroy.onit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paulconroy.onit.model.BusData;
import com.example.paulconroy.onit.model.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by paulconroy on 22/12/2015.
 */
public class Alarm extends Activity {

    public List<BusData> list;
    Model m = Model.getInstance();
    GetBusAsync client;
    EditText busNumber;
    Button submit;
    Button endAlarm;
    Intent i;
    Calendar cal;
    Controller mController;
    AlarmManager alarm;
    PendingIntent pintent;
    LinearLayout mainFrame;
    PowerManager mgr;
    PowerManager.WakeLock wakeLock;
    TextView instructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_alarm);

        mainFrame = (LinearLayout) findViewById(R.id.mainFrame);
        mController = new Controller(this);
        busNumber = (EditText) findViewById(R.id.number);
        instructions = (TextView) findViewById(R.id.instructions);
        submit = (Button) findViewById(R.id.submit);
        // endAlarm = (Button) findViewById(R.id.stopAlarm);
        list = new ArrayList<BusData>();

        Animation frameAnim = AnimationUtils.loadAnimation(this, R.anim.scale_from_corner);

        frameAnim.setStartOffset(500);

        mainFrame.startAnimation(frameAnim);

        loadAnimation();

        cal = Calendar.getInstance();

        list = m.getBusList();
        //Toast.makeText(this,"Onnit!",Toast.LENGTH_LONG).show();

        mgr = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "bbbb");
        wakeLock.acquire();


    }

    public void submit(View v) {
        if (busNumber.getText().toString().trim().length() == 0) {
            busNumber.setHint("ahem...");
        } else {
            String number = busNumber.getText().toString();
            m.addBusNumber(number);

            //REMOVE WHEN TESTING
            client = new GetBusAsync(this);
            client.execute("http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=" + number + "&format=json");


//            Intent i = new Intent(this,Result.class);
//            startActivity(i);

        }


    }

    public void loadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.text_alpha);
        animation.setStartOffset(700);
        animation2.setStartOffset(700);
        instructions.startAnimation(animation);
        busNumber.startAnimation(animation2);

    }
}
