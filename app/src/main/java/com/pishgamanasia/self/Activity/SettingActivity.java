package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pishgamanasia.self.Helper.SettingHelper;
import com.pishgamanasia.self.Helper.Webservice;
import com.pishgamanasia.self.R;

public class SettingActivity extends Activity {

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context=this;
        final SettingHelper setting = new SettingHelper(context);

        final TextView tvDeviceId = (TextView) findViewById(R.id.textview_current_device_id);
        final TextView tvServerAddress = (TextView) findViewById(R.id.textview_current_server_address);

        RelativeLayout rlServerAddress = (RelativeLayout) findViewById(R.id.option_server_address);
        RelativeLayout rlDeviceId = (RelativeLayout) findViewById(R.id.option_device_id);

        tvDeviceId.setText(setting.getOption("deviceId"));
        tvServerAddress.setText(setting.getOption("serverAddress"));


        rlServerAddress.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("آدرس سرور");
                final EditText input = new EditText(context);
                input.setText(setting.getOption("serverAddress"));
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setting.setOption("serverAddress",input.getText().toString());
                        tvServerAddress.setText(setting.getOption("serverAddress"));
                    }
                });
                builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });


        rlDeviceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("کد دستگاه");
                final EditText input = new EditText(context);
                input.setText(setting.getOption("deviceId"));
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setting.setOption("deviceId",input.getText().toString());
                        tvDeviceId.setText(setting.getOption("deviceId"));
                    }
                });
                builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
