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

import com.pishgamanasia.self.Callback.CallBack;
import com.pishgamanasia.self.Callback.CallBackFunction;
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

        if (Account.getInstant(this).alreadyHaveToken()) {

            callMainActivity();

        }


        txtUsername = (EditText) findViewById(R.id.etxt_fragmentLogin_username);
        txtPassword = (EditText) findViewById(R.id.etxt_fragmentLogin_password);

        loaderBar = (ProgressBar) findViewById(R.id.pgb_fragmentLogin_loader);

        panel = (LinearLayout) findViewById(R.id.ll_fragmentLogin_panel);

//        FontHelper.SetFont(context, Fonts.MAIN_FONT,btnLogin, Typeface.BOLD);

        imgv = (ImageView) findViewById(R.id.imgv_fragmentLogin_setting);
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("آدرس سرور را وارد نمایید");

                final EditText input = new EditText(context);
                input.setText(Webservice.getSERVER_ADDRESS());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Webservice.modifyServerAddress(input.getText().toString(), context);
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

        Webservice.Login(this, username, password, new CallBack<String>() {
            @Override
            public void onSuccess(String token) {

                // Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
                Account.getInstant(context).storeToken(token);
                callMainActivity();

            }

            @Override
            public void onError(String err) {

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.view_not_valid);
                panel.startAnimation(animation);

                btnLogin.setVisibility(View.VISIBLE);
                imgv.setVisibility(View.VISIBLE);

                loaderBar.setVisibility(View.GONE);

                HandleError.HandleError(context, err, new CallBackFunction() {
                    @Override
                    public void execute() {

                    }
                });
                //Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();


            }


        });

    }



    //-----------------------------------------------------Functions}

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


    //validations-----------------------------------------------------

    private boolean LoginValidation(String username, String password, ValidationMessage validationMessage) {

        return true;
    }

    private void callMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
