package com.pishgamanasia.self.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by aliparsa on 8/10/2014.
 */
public class AccountHelper {


    static AccountHelper instant;
    String token;


    Context context;


    public AccountHelper() {

    }

    public static AccountHelper getInstant(Context context) {

        if (instant == null && context != null) {
            instant = new AccountHelper();
            instant.context = context;
        }

        return instant;

    }

    public void storeToken(String token) {
        this.token = token;
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {

        if (token != null && token.length() > 1)
            return token;

        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        token = app_preferences.getString("token", null);
        return token;

    }

    public boolean alreadyHaveToken() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        String token;
        token = app_preferences.getString("token", null);

        if (token == null) return false;

        if (token.length() < 1)
            return false;
        else
            return true;

    }

    public void clearToken() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("token", "");
        editor.commit();
    }
}
