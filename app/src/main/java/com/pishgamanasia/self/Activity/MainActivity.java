package com.pishgamanasia.self.Activity;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.Helper.AccountHelper;
import com.pishgamanasia.self.Helper.FontHelper;
import com.pishgamanasia.self.Helper.SettingHelper;
import com.pishgamanasia.self.Helper.TimerHelper;
import com.pishgamanasia.self.Helper.Webservice;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private TextView servedCounter;
    private TextView timer;
    private TextView buffetName;
    private TextView montazerKart;
    private TextView userName;
    private Button send_card_id;
    private Context context;
    private ImageView vazifeImage;
    private ImageView exit;
    private ImageView imgv_setting;
    private EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;






        LoginInfo loginInfo = (LoginInfo) getIntent().getSerializableExtra("loginInfo");



        montazerKart = (TextView) findViewById(R.id.textViewMontazerKart);
        timer = (TextView) findViewById(R.id.txt_timer);
        buffetName = (TextView) findViewById(R.id.txt_buffet_name);
        userName = (TextView) findViewById(R.id.txt_username);
        send_card_id = (Button) findViewById(R.id.send_card_id);
        exit = (ImageView) findViewById(R.id.imagev_exit);
        imgv_setting = (ImageView) findViewById(R.id.imgv_setting);
        code = (EditText) findViewById(R.id.code);

        buffetName.setText(loginInfo.getResturantName());
        userName.setText(loginInfo.getName());


        //log activity
        vazifeImage = (ImageView) findViewById(R.id.vazife);
        vazifeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LogActivity.class);
                startActivity(intent);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountHelper.getInstant(context).clearToken();
                ((MainActivity) context).finish();
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);

            }
        });

        setTimer();

        imgv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SettingActivity.class);
                startActivity(intent);
            }
        });

        send_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DeliveryActivity.class);
                //intent.putExtra("cardId","2551444574");
                if (code.length()>1)
                intent.putExtra("cardId",code.getText().toString());
                startActivity(intent);

            }
        });


        // change font
        FontHelper.SetFont(context, FontHelper.Fonts.MAIN_FONT,timer, Typeface.BOLD);
        FontHelper.SetFont(context, FontHelper.Fonts.MAIN_FONT,buffetName, Typeface.BOLD);
        FontHelper.SetFont(context, FontHelper.Fonts.MAIN_FONT,userName, Typeface.BOLD);
        FontHelper.SetFont(context, FontHelper.Fonts.MAIN_FONT,montazerKart, Typeface.BOLD);
        FontHelper.SetFont(context, FontHelper.Fonts.MAIN_FONT,code, Typeface.BOLD);



        // clear keyboard on start
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    private void msgUser(String errMessage) {
        Toast.makeText(context,errMessage,Toast.LENGTH_SHORT).show();
    }

    private void setTimer() {

        TimerHelper.timerFactory(1000,2 , new TimerHelper.TimerFunction() {
            @Override
            public void tick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Calendar c = Calendar.getInstance();
                        int hr = c.get(Calendar.HOUR);
                        int min = c.get(Calendar.MINUTE);
                        int sec = c.get(Calendar.SECOND);
                        timer.setText(hr+" : "+ (min < 10 ? "0"+min : min) +" : "+ (sec < 10 ? "0"+sec : sec));
                    }
                });
            }
        });

    }

}
