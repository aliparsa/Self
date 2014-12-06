package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private TextView servedCounter;
    private TextView timer;
    private TextView buffetName;
    private TextView userName;
    private Button send_card_id;
    private ImageView vazifeImage;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginInfo loginInfo = (LoginInfo) getIntent().getSerializableExtra("loginInfo");


        servedCounter = (TextView) findViewById(R.id.txt_served_count);
        timer = (TextView) findViewById(R.id.txt_timer);
        buffetName = (TextView) findViewById(R.id.txt_buffet_name);
        userName = (TextView) findViewById(R.id.txt_username);
        send_card_id = (Button) findViewById(R.id.send_card_id);

        buffetName.setText(loginInfo.getResturantName());
        userName.setText(loginInfo.getName());

        context = this;

        //log activity
        vazifeImage = (ImageView) findViewById(R.id.vazife);
        vazifeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LogActivity.class);
                startActivity(intent);
            }
        });

        setTimer();

        send_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
