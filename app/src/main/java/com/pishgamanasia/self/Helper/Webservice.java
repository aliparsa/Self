package com.pishgamanasia.self.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.Interface.ResponseHandler;
import com.pishgamanasia.self.DataModel.ServerResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {

    private static final int RESULT_OK =100;
    private static final int LOGIN_FAILED=101;


    //this is sparta


    public static String getSERVER_ADDRESS() {
        return SERVER_ADDRESS;
    }

    private static String SERVER_ADDRESS = "http://192.168.0.14:6061";
    private static String SERVER_ADDRESS_POSTFIX = "/areas/buffet/service/webserviceAndroid.asmx?op=GetStep1";

    //-----------------------------------------------------------------------------
    public static String getWEBSERVICE_ADDRESS() {
        return SERVER_ADDRESS + SERVER_ADDRESS_POSTFIX;
    }

    //--------------------------------------------------------------------------
    private static void prepareServerAddress(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String server_address = preferences.getString("server_address3", null);

        if (server_address != null) {
            SERVER_ADDRESS = server_address;
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("server_address3", SERVER_ADDRESS);
            editor.apply();
        }


    }

    //-----------------------------------------------------------------------------
    public static void Login(Context context,final String username, final String password, final String deviceId, final CallBack<LoginInfo> callback) {

        try {
            final String NAMESPACE = SERVER_ADDRESS+"/Areas/Buffet/Service/";
            final String METHOD_NAME = "GetStep1";
            final String URL = SERVER_ADDRESS+"/areas/buffet/service/webserviceAndroid.asmx?op=GetStep1";
            final String SOAP_ACTION =SERVER_ADDRESS+ "/Areas/Buffet/Service/GetStep1";

            SoapHelper soapHelper = new SoapHelper(context,NAMESPACE, METHOD_NAME, URL, SOAP_ACTION);

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<String>();

            names.add("username");
            values.add(username);

            names.add("password");
            values.add(password);

            names.add("deviceId");
            values.add(deviceId);

            soapHelper.SendRequestToServer(names,values, new CallBack<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {

                        int resultCode = resultCode = result.getInt("ResultCode");

                        switch (resultCode) {
                            case RESULT_OK: {
                                String token = result.getString("token");
                                String name = result.getString("name");
                                int resturantId = result.getInt("restaurantId");
                                String resturantName = result.getString("restaurantName");
                                String deliverPersonel = result.getString("deliverPersonel");
                                callback.onSuccess(new LoginInfo(token, name, resturantId, resturantName, deliverPersonel));
                                break;
                            }
                            case LOGIN_FAILED: {
                                callback.onError("login failed");
                                break;
                            }
                            default: {
                                callback.onError("server response is not valid ");
                                break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorMessage) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------------------------------
    public static void sendCard(Context context,final String cardNo, final CallBack<Object> callback) {

        try {
            final String NAMESPACE = SERVER_ADDRESS+"/Areas/Buffet/Service/";
            final String METHOD_NAME = "GetStep2";
            final String URL = SERVER_ADDRESS+"/areas/buffet/service/webserviceAndroid.asmx?op=GetStep2";
            final String SOAP_ACTION =SERVER_ADDRESS+ "/Areas/Buffet/Service/GetStep2";

            SoapHelper soapHelper = new SoapHelper(context,NAMESPACE, METHOD_NAME, URL, SOAP_ACTION);

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<String>();

            names.add("token");
            values.add(Account.getInstant(context).getToken());

            names.add("cardNo");
            values.add(cardNo);

            names.add("restaurantId");
            values.add("1");

            names.add("deliverPersonel");
            values.add("1");

            soapHelper.SendRequestToServer(names,values, new CallBack<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {

                        int resultCode = resultCode = result.getInt("ResultCode");

                        switch (resultCode) {
                            case RESULT_OK: {

                                  //callback.onSuccess();
                                break;
                            }

                            default: {
                                callback.onError("server response is not valid ");
                                break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorMessage) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------
    public static void modifyServerAddress(String serverAddress, Context context) {
        if (serverAddress.length() < 1) return;
        SERVER_ADDRESS = serverAddress;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("server_address3", SERVER_ADDRESS);
        editor.apply();
    }

    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------

}