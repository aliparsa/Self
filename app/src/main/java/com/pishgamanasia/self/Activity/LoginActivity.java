package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.Helper.SettingHelper;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.Interface.CallBackFunction;
import com.pishgamanasia.self.Helper.Account;
import com.pishgamanasia.self.Helper.HandleError;
import com.pishgamanasia.self.Helper.ValidationMessage;
import com.pishgamanasia.self.Helper.Webservice;
import com.pishgamanasia.self.R;


public class LoginActivity extends Activity {


    EditText txtUsername;
    EditText txtPassword;
    ImageView imgv;
    Button btnLogin;
    ProgressBar loaderBar;
    LinearLayout panel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        // set Default Setting after Install App
        SettingHelper settingHelper = new SettingHelper(context);
        if (settingHelper.getOption("firstTime")==null){
            // Avalin Bar Ast
            settingHelper.setOption("firstTime","1");
            settingHelper.setOption("deviceId","x1");
            settingHelper.setOption("serverAddress","http://192.168.0.1:1111");

        }else{
            // Avalin Bar Nist
        }



        txtUsername = (EditText) findViewById(R.id.etxt_fragmentLogin_username);
        txtPassword = (EditText) findViewById(R.id.etxt_fragmentLogin_password);

        loaderBar = (ProgressBar) findViewById(R.id.pgb_fragmentLogin_loader);

        panel = (LinearLayout) findViewById(R.id.ll_fragmentLogin_panel);

//        FontHelper.SetFont(context, Fonts.MAIN_FONT,btnLogin, Typeface.BOLD);

        imgv = (ImageView) findViewById(R.id.imgv_fragmentLogin_setting);

        imgv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context,LogActivity.class);
                startActivity(intent);
                return true;
            }
        });
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SettingActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.btn_fragmentLogin_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public ValidationMessage validationMessage;

            @Override
            public void onClick(View view) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (LoginValidation(username, password, validationMessage))
                    loginClicked(username, password);
            }
        });


    }


    private void loginClicked(String username, String password) {

        btnLogin.setVisibility(View.GONE);
        imgv.setVisibility(View.GONE);
        loaderBar.setVisibility(View.VISIBLE);

        Webservice.Login(context,username,password,"x12",new CallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo result) {
                Account.getInstant(context).storeToken(result.getToken());
                callMainActivity(result);

            }

            @Override
            public void onError(String errorMessage) {
                btnLogin.setVisibility(View.VISIBLE);
                imgv.setVisibility(View.VISIBLE);
                loaderBar.setVisibility(View.GONE);
                msgUser(errorMessage);
            }
        });

    }



    //-----------------------------------------------------Functions}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //validations-----------------------------------------------------

    private boolean LoginValidation(String username, String password, ValidationMessage validationMessage) {

        return true;
    }

    private void callMainActivity(LoginInfo loginInfo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("loginInfo",loginInfo);
        startActivity(intent);
        this.finish();
    }

    private void msgUser(String errMessage) {
        Toast.makeText(context, errMessage, Toast.LENGTH_SHORT).show();
    }
}
