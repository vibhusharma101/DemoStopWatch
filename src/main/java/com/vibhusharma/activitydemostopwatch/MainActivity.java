package com.vibhusharma.activitydemostopwatch;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView stopWatchTextView;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;
    private int seconds;
    private  boolean running ;

    private boolean wasRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {

            seconds = savedInstanceState.getInt("Seconds");
            running = savedInstanceState.getBoolean("Running");
            wasRunning = savedInstanceState.getBoolean("WasRunning");

        }
        else
        {
            seconds=0;
            running=false;
        }


        stopWatchTextView = findViewById(R.id.stopWatchTextView);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        resetBtn = findViewById(R.id.resetBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
running = true;
wasRunning=true;

            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running=false;
                wasRunning=false;
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running= false;
                wasRunning=false;
                seconds = 0;
            }
        });

runTimer();




    }

    @Override
    protected void onResume() {
        super.onResume();

        if(wasRunning==true)
        {
            running=true ;
        }






    }

    @Override
    protected void onPause() {
        super.onPause();

        running = false;




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Seconds",seconds);
        outState.putBoolean("Running",running);
        outState.putBoolean("WasRunning",wasRunning);




    }

    private void runTimer()
    {

       final Handler handler = new Handler();
       handler.post(new Runnable() {
           @Override
           public void run() {

               int hours = seconds/3600;
               int minutes = (seconds%3600)/60;
               int secs = seconds%60;

               String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
               stopWatchTextView.setText(time);

               if(running)
               {
                   seconds++;
               }

               handler.postDelayed(this,1000);


           }
       });


    }


}
