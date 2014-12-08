package com.pishgamanasia.self.Helper;

import android.content.Context;


import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.DataModel.Personnel;
import com.pishgamanasia.self.DataModel.Reserve;
import com.pishgamanasia.self.DataModel.ServerCardResponse;
import com.pishgamanasia.self.Interface.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {

    private static final int RESULT_OK =100;
    private static final int RESULT_ERROR =101;




    //-----------------------------------------------------------------------------
    public static void Login(Context context,final String username, final String password, final String deviceId, final CallBack<LoginInfo> callback) {

        try {
            SettingHelper setting = new SettingHelper(context);
            String SERVER_ADDRESS = setting.getOption("serverAddress");

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
                            case RESULT_ERROR: {
                                callback.onError("نام و یا کلمه عبور اشتباه است");
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
                    callback.onError(errorMessage);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------
    public static void sendCard(Context context,final String cardNo, final CallBack<ServerCardResponse> callback) {

        try {
            SettingHelper setting = new SettingHelper(context);
            String SERVER_ADDRESS = setting.getOption("serverAddress");

            final String NAMESPACE = SERVER_ADDRESS+"/Areas/Buffet/Service/";
            final String METHOD_NAME = "GetStep2";
            final String URL = SERVER_ADDRESS+"/areas/buffet/service/webserviceAndroid.asmx?op=GetStep2";
            final String SOAP_ACTION =SERVER_ADDRESS+ "/Areas/Buffet/Service/GetStep2";

            SoapHelper soapHelper = new SoapHelper(context,NAMESPACE, METHOD_NAME, URL, SOAP_ACTION);

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<String>();

            names.add("token");
            values.add(AccountHelper.getInstant(context).getToken());

            names.add("cardNo");
            values.add(cardNo);


            soapHelper.SendRequestToServer(names,values, new CallBack<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {

                        int resultCode = resultCode = result.getInt("ResultCode");

                        switch (resultCode) {
                            case RESULT_OK: {

                                ArrayList<Personnel> personnels = Personnel.getArrayFromJson(result.getString("Personels"));
                                ArrayList<Reserve> reserves = Reserve.getArrayFromJson(result.getString("Reserves"));
                                String message = result.getString("Message");
                                String status = result.getString("Status");
                                callback.onSuccess(new ServerCardResponse(personnels,reserves,message,status));
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
                    callback.onError(errorMessage);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------------------------
    public static void sendTahvil(Context context,String strJsonListReserve,final CallBack callback){

        try {

            SettingHelper setting = new SettingHelper(context);
            String SERVER_ADDRESS = setting.getOption("serverAddress");

            final String NAMESPACE = SERVER_ADDRESS+"/Areas/Buffet/Service/";
            final String METHOD_NAME = "GetStep3";
            final String URL = SERVER_ADDRESS+"/areas/buffet/service/webserviceAndroid.asmx?op=GetStep3";
            final String SOAP_ACTION =SERVER_ADDRESS+ "/Areas/Buffet/Service/GetStep3";

            SoapHelper soapHelper = new SoapHelper(context,NAMESPACE, METHOD_NAME, URL, SOAP_ACTION);

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<String>();

            names.add("token");
            values.add(AccountHelper.getInstant(context).getToken());

            names.add("listReserveId");
            values.add(strJsonListReserve);


            soapHelper.SendRequestToServer(names,values, new CallBack<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {

                        int resultCode = resultCode = result.getInt("ResultCode");

                        switch (resultCode) {
                            case RESULT_OK: {
                                callback.onSuccess(null);
                                break;
                            }
                            case RESULT_ERROR: {
                                callback.onError("server return error");
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



}