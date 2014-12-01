package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pishgamanasia.self.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private TextView servedCounter;
    private TextView timer;
    private TextView buffetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        servedCounter = (TextView) findViewById(R.id.txt_served_count);
        timer = (TextView) findViewById(R.id.txt_timer);
        buffetName = (TextView) findViewById(R.id.txt_buffet_name);


        setTimer();
    }

    private void setTimer() {


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Date date = new Date();
                        timer.setText(date.getHours() + " : " + date.getMinutes());
                    }
                });

            }
        }, 1000);

    }

}
