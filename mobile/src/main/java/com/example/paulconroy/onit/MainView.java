package com.example.paulconroy.onit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulconroy.onit.Database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by paulconroy on 17/12/2015.
 */
public class MainView extends Activity {

    Handler handler;
    DatabaseHandler db;
    ImageButton busTimesBTN;
    ImageButton alarmsBTN;
    ImageButton favsBTN;
    ImageButton settingsBTN;
    TextView news;
    TextView busTimesText;
    TextView alarmText;
    TextView favText;
    TextView settingsText;
    FrameLayout busTimesLayout;
    Boolean removeNews;
    ImageButton challengeBTN;
    ImageButton arcadeBTN;
    ImageView transition;
    ImageView transition2;
    List<ImageButton> BTNList;
    List<TextView> BTNTextList;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.landing_layout);
        db = new DatabaseHandler(this);

        news = (TextView) findViewById(R.id.newsText);
        busTimesText = (TextView) findViewById(R.id.busTimesText);
        alarmText = (TextView) findViewById(R.id.alarmText);
        favText = (TextView) findViewById(R.id.favText);
        //settingsText = (TextView) findViewById(R.id.settingsText);


        busTimesLayout = (FrameLayout) findViewById(R.id.busTimesLayout);

        news.setVisibility(View.GONE);

        busTimesBTN = (ImageButton) findViewById(R.id.busTimesBTN);
        alarmsBTN = (ImageButton) findViewById((R.id.alarmBTN));
        favsBTN = (ImageButton) findViewById(R.id.favBTN);
        //settingsBTN = (ImageButton) findViewById((R.id.settingsBTN));


        BTNList = new ArrayList<ImageButton>();
        BTNTextList = new ArrayList<TextView>();


        BTNList.add(busTimesBTN);
        BTNList.add(alarmsBTN);
        BTNList.add(favsBTN);
        //BTNList.add(settingsBTN);

        BTNTextList.add(busTimesText);
        BTNTextList.add(alarmText);
        BTNTextList.add(favText);
        //BTNTextList.add(settingsText);

        for (ImageButton BTN : BTNList) {
            BTN.setVisibility(View.GONE);
        }

        for (TextView text : BTNTextList) {
            text.setVisibility(View.GONE);
        }

        loadAnimation();


        removeNews = true;
        startNews(news, removeNews, "Service is online");


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void startNews(final TextView news, Boolean animationEnd, String text) {
        news.setVisibility(View.VISIBLE);
        news.setText(text);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.newsfeed_slide);

        if (animationEnd) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    news.setVisibility(View.GONE);

                    startNews(news, removeNews, "Built by Paul Conroy");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        news.startAnimation(animation);
    }

    public void loadAnimation() {

        int i = 1;
        for (ImageButton BTN : BTNList) {
            for (TextView text : BTNTextList) {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
                Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.text_alpha);
                BTN.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                animation.setStartOffset(i * 50);
                animation2.setStartOffset(i * 100);
                BTN.startAnimation(animation);
                text.startAnimation(animation2);
                i++;
            }
        }
    }

    public void test(View v) {
        //Toast.makeText(this,"CLICKED",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        overridePendingTransition(R.anim.open_trans, R.anim.close_trans);
    }

    public void alarm(View v) {
        //Toast.makeText(this,"CLICKED",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, Alarm.class);
        startActivity(i);

        overridePendingTransition(R.anim.open_trans, R.anim.close_trans);
    }

    public void favourites(View v) {
        Intent i = new Intent(this, Favourites.class);
        startActivity(i);
    }
}

