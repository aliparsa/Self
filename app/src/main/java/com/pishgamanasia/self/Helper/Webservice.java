package com.pishgamanasia.self.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.Interface.ResponseHandler;
import com.pishgamanasia.self.DataModel.ServerResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {

    public static final int LOGIN_OK=200;
    public static final int LOGIN_FAILED=201;


    //this is sparta


    public static String getSERVER_ADDRESS() {
        return SERVER_ADDRESS;
    }

    private static String SERVER_ADDRESS = "http://192.168.0.11:6061";
    private static String SERVER_ADDRESS_POSTFIX = "/areas/buffet/service/webserviceAndroid.asmx?op=GetStep1";
    //-----------------------------------------------------------------------------

    public static String getWEBSERVICE_ADDRESS() {
        return SERVER_ADDRESS + SERVER_ADDRESS_POSTFIX;
    }

    public static void prepareServerAddress(Context context) {

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
    public static void soapLogin(Context context, final String username, final String password, final String deviceId, final CallBack<LoginInfo> callback) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    final String NAMESPACE = "http://192.168.0.11:6061/Areas/Buffet/Service/";
                    final String METHOD_NAME1 = "GetStep1";
                    final String URL = "http://192.168.0.11:6061/areas/buffet/service/webserviceAndroid.asmx?op=GetStep1";
                    final String SOAP_ACTION1 = "http://192.168.0.11:6061/Areas/Buffet/Service/GetStep1";

                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);

                    request.addProperty("username", username);
                    request.addProperty("password", password);
                    request.addProperty("deviceId", deviceId);

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    //this is the actual part that will call the webservice
                    androidHttpTransport.call(SOAP_ACTION1, envelope);

                    // Get the SoapResult from the envelope body.
                    SoapObject result = (SoapObject) envelope.bodyIn;

                    if (result != null) {
                        // Get result and get result code and check it
                        int resultCode = Integer.parseInt(result.getProperty("resultCode").toString());

                        switch (resultCode) {
                            case LOGIN_OK: {

                                String token = (String) result.getProperty("token");
                                String name = (String) result.getProperty("name");
                                int resturantId = Integer.parseInt(result.getProperty("resturantId").toString());
                                String resturantName = (String) result.getProperty("resturantName");
                                String deliverPersonel = (String) result.getProperty("deliverPersonel");
                                callback.onSuccess(new LoginInfo(token, name, resturantId, resturantName, deliverPersonel));

                            }
                            case LOGIN_FAILED: {
                                callback.onError("login failed");
                            }
                            default: {
                                callback.onError("server response is not valid ");
                            }
                        }
                    } else {

                        callback.onError("result is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onError(e.getMessage());
                }

            }
        });
        thread.start();


    }



//    public static void getProjects(Context context, final CallBack<ArrayList<Chart>> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_projects")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(chartList);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    callBack.onError(e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", "Error");
//                callBack.onError(err);
//            }
//        });
//    }
//
//    // DONE
//    //-----------------------------------------------------------------------------
//    public static void GetChildOfID(Context context, final int id, final CallBack callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_child_of_id"),
//                new BasicNameValuePair("id", id + "")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(chartList);
//                            break;
//                        }
//
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                callBack.onError(err);
//            }
//        });
//
//    }

    // DONE
    //------------------------------------------------------------------------
    public static void Login(Context context, String username, String password, final CallBack callback) {

        prepareServerAddress(context);
        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("password", password),
                new BasicNameValuePair("deviceId", "rx12")


        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {


                    JSONObject jsonObject = new JSONObject(response.getResult());

                    if (jsonObject.has("result")) {
                        if (jsonObject.get("result").equals("ok")) {
                            callback.onSuccess(jsonObject.get("token").toString());
                        } else if (jsonObject.get("result").equals("error")) {

                            callback.onError("err7");
                        }
                    } else {
                        callback.onError("err8");
                    }


//                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                    callback.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();

                    callback.onError("err9");

                } catch (Exception e) {
                    e.printStackTrace();

                    callback.onError("err10");
                }


            }

            @Override
            public void error(String err) {
                callback.onError(err);
            }
        });

    }

    // DONE
    //-------------------------------------------------------------------------------
//    public static void getTaskListByWorkId(Context context, final int id, final CallBack callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_task_of_id"),
//                new BasicNameValuePair("id", id + "")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(chartList);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void error(String err) {
//
//                callBack.onError(err);
//            }
//        });
//
//    }
//
//    // DONE
//    //-------------------------------------------------------------------------------
//    public static void searchPersonnel(Context context, String str, final CallBack<ArrayList<Personnel>> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "search_personnel"),
//                new BasicNameValuePair("query", str)
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Personnel> perList = Personnel.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(perList);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//            }
//        });
//    }
//
//    // DONE with error
//    //-------------------------------------------------------------------------------
//    public static void getReportListByWorkId(Context context, int id, final CallBack callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_report_of_id"),
//                new BasicNameValuePair("id", id + "")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Report> reportList = Report.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(reportList);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / getReportListByWorkId ");
//
//            }
//        });
//
//    }
//
//    // DONE
//    //-------------------------------------------------------------------------------
//    public static void addPersonnelToWork(Context context, int personnelId, int workId, Task task, final CallBack<ServerResponse> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "add_personnel_to_work"),
//                new BasicNameValuePair("personnel_id", personnelId + ""),
//                new BasicNameValuePair("chart_id", workId + ""),
//                new BasicNameValuePair("name", task.getName()),
//                new BasicNameValuePair("budget", task.getPrice()),
//                new BasicNameValuePair("start_date", task.getStart_date()),
//                new BasicNameValuePair("end_date", task.getEnd_date()),
//                new BasicNameValuePair("total_work", task.getKolKar()),
//                new BasicNameValuePair("work_unit", task.getVahedKar()),
//                new BasicNameValuePair("description", task.getTozihat())
//
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONObject jsonObject = new JSONObject(response.getResult());
//                            callBack.onSuccess(response);
//                            break;
//                        }
//                        case SC_BAD_REQUEST: {
//                            callBack.onError("DateOrPeopleInvalid");
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    callBack.onError("exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                callBack.onError(err);
//                Log.e("ali", " webservice / addPersonnelToWork ");
//            }
//        });
//
//    }
//
//    // DONE
//    //-------------------------------------------------------------------------------
//    public static void saveWorkReport(Context context, final Report report, final String[] imagePaths, final ProgressCallBack callBack) {
//        prepareServerAddress(context);
//        final HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        if (imagePaths != null) {
//
//            int imageCount = 0;
//            for (int i = 0; i < imagePaths.length; i++) {
//
//                if (imagePaths[i] != null)
//                    imageCount++;
//            }
//
//            final String now = new Date().toString();
//            for (int i = 0; i < imagePaths.length; i++) {
//
//                final String imagePath = imagePaths[i];
//                final int finalImageCount = imageCount;
//
//                if (imagePath != null) {
//
//                    uploadFile(context, imagePath, new CallBackUpload() {
//
//                        @Override
//                        public void onSuccess(Object result, String tag) {
//
//                            int uploaded = uploadHelper(tag, (String) result);
//
//                            callBack.onProgress(uploaded, finalImageCount, result);
//
//                            if (uploaded == finalImageCount) {
//                                sendMainRequest(tag);
//                            }
//                        }
//
//                        void sendMainRequest(String tag) {
//
//                            ArrayList<String> imagesOfTag = uploadCountMap.get(tag);
//                            String imagesJson = "";
//                            for (String imageAddress : imagesOfTag) {
//
//                                imagesJson += imageAddress + ";";
//                            }
//
//                            BasicNameValuePair[] arr = {
//                                    new BasicNameValuePair("tag", "save_report"),
//                                    new BasicNameValuePair("id", report.getChart().getId() + ""),
//                                    new BasicNameValuePair("report", report.getReport() + ""),
//                                    new BasicNameValuePair("percent_work", report.getPercent() + ""),
//                                    new BasicNameValuePair("date", report.getDate() + ""),
//                                    new BasicNameValuePair("images", imagesJson)
//                            };
//
//                            helper.postHttp(arr, new ResponseHandler() {
//                                @Override
//                                public void handleResponse(ServerResponse response) {
//
//                                    try {
//
//                                        switch (response.getStatusCode()) {
//                                            case SC_UNAUTHORIZED: {
//                                                callBack.onError("UNAUTHORIZED");
//                                                break;
//                                            }
//                                            case SC_OK: {
//                                                JSONObject jsonObject = new JSONObject(response.getResult());
//                                                callBack.onSuccess(response);
//                                                break;
//                                            }
//                                        }
//
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//
//                                @Override
//                                public void error(String err) {
//                                    Log.e("ali", " webservice / saveWorkReport ");
//                                    callBack.onError("error 1418");
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onError(String errorMessage) {
//                            Log.e("ali", " webservice / saveWorkReport ");
//                            callBack.onError("error 1419");
//                        }
//
//                    }, now);
//                }
//            }
//        } else {
//            BasicNameValuePair[] arr = {
//                    new BasicNameValuePair("tag", "save_report"),
//                    new BasicNameValuePair("id", report.getChart().getId() + ""),
//                    new BasicNameValuePair("report", report.getReport() + ""),
//                    new BasicNameValuePair("percent_work", report.getPercent() + ""),
//                    new BasicNameValuePair("date", report.getDate() + ""),
//                    new BasicNameValuePair("images", "")
//            };
//
//            helper.postHttp(arr, new ResponseHandler() {
//                @Override
//                public void handleResponse(ServerResponse response) {
//
//                    try {
//
//
//                        switch (response.getStatusCode()) {
//                            case SC_UNAUTHORIZED: {
//                                callBack.onError("UNAUTHORIZED");
//                                break;
//                            }
//                            case SC_OK: {
//                                JSONObject jsonObject = new JSONObject(response.getResult());
//                                callBack.onSuccess(response);
//                                break;
//                            }
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void error(String err) {
//                    Log.e("ali", " webservice / saveWorkReport ");
//                    callBack.onError("error 1422");
//                }
//            });
//        }
//
//    }
//
//    //-------------------------------------------------------------------------------
//    static Map<String, ArrayList<String>> uploadCountMap = new HashMap<String, ArrayList<String>>();
//
//    private static int uploadHelper(String tag, String imageName) {
//
//        ArrayList<String> list;
//
//        if (uploadCountMap.containsKey(tag)) {
//            list = uploadCountMap.get(tag);
//            list.add(imageName);
//
//        } else {
//            list = new ArrayList<String>();
//            list.add(imageName);
//
//            uploadCountMap.put(tag, list);
//        }
//
//        return list.size();
//    }
//
//    //-------------------------------------------------------------------------------
//    public static void getWorkUnitList(Context context, final CallBack<ArrayList<WorkUnit>> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "work_units")
//        };
//
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<WorkUnit> reportList = WorkUnit.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(reportList);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / getReportListByWorkId ");
//                callBack.onError(err);
//
//            }
//        });
//
//    }
//
//    // DONE
//    //------------------------------------------------------------------------------
//    public static void removeTask(Context context, int task_id, final CallBack<ServerResponse> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "remove_task"),
//                new BasicNameValuePair("id", task_id + ""),
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONObject jsonObject = new JSONObject(response.getResult());
//                            callBack.onSuccess(response);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / addPersonnelToWork ");
//                callBack.onError(err);
//            }
//        });
//
//    }
//
//
//    // NOT DONE
//    //------------------------------------------------------------------------------
//    public static void sendTaradod(Context context, List<Taradod> taradodList, final CallBack<ServerResponse> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        String taradodJSON = Taradod.convertArrayToJson(taradodList).toString();
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "taradod"),
//                new BasicNameValuePair("value", taradodJSON)
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONObject jsonObject = new JSONObject(response.getResult());
//                            callBack.onSuccess(response);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / send Taradod ");
//                callBack.onError(err);
//            }
//        });
//
//    }
//
//    //------------------------------------------------------------------------------
//    public static void sendAnbarTransaction(Context context, List<AnbarTransaction> anbarTransactions, final CallBack<ServerResponse> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        String anbarTransactionJSON = AnbarTransaction.convertArrayToJson(anbarTransactions).toString();
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "insert_warhouse"),
//                new BasicNameValuePair("value", anbarTransactionJSON)
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONObject jsonObject = new JSONObject(response.getResult());
//                            callBack.onSuccess(response);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / send anbar transaction ");
//                callBack.onError(err);
//            }
//        });
//
//    }
//    //-------------------------------------------------------------------------------
//    public static void sendFaliat(Context context, List<Faliat> faliats, final CallBack<ServerResponse> callBack) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        String faliatJSON = Faliat.convertArrayToJson(faliats).toString();
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "faaliat"),
//                new BasicNameValuePair("value", faliatJSON)
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONObject jsonObject = new JSONObject(response.getResult());
//                            callBack.onSuccess(response);
//                            break;
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    callBack.onError("Exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / send Faaliat ");
//                callBack.onError(err);
//            }
//        });
//
//    }
//
//    // DONE
//    //-------------------------------------------------------------------------------
//    public static void uploadFile(Context context, String filePath, final CallBackUpload callBack, final String tag) {
//        prepareServerAddress(context);
//        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//        helper.upload(filePath, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//                    JSONObject jsonObject = new JSONObject(response.getResult());
//                    //TODO get image in server name
//
//                    String result = JsonHelper.getStringS(jsonObject, "result", "");
//                    String imageName = "";
//                    if (result.equals("ok")) {
//
//                        imageName = JsonHelper.getStringS(jsonObject, "image_address", "");
//                    } else {
//                        callBack.onError("notSaved");
//                    }
//
//                    callBack.onSuccess(imageName, tag);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    callBack.onError("exception");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//                Log.e("ali", " webservice / saveWorkReport ");
//                callBack.onError("err12");
//            }
//        });
//    }
//
//    public static String getServerAddress() {
//        return SERVER_ADDRESS;
//    }
//
    public static void modifyServerAddress(String serverAddress, Context context) {
        if (serverAddress.length() < 1) return;
        SERVER_ADDRESS = serverAddress;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("server_address3", SERVER_ADDRESS);
        editor.apply();
    }

//    //--------------------------------------------------------------------------------
//    public static void getHomePageInfo(Context context, final CallBack<JSONArray> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_home_info")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            //ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(jsonArray);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    callBack.onError("exception getProjects");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", "Error");
//                callBack.onError(err);
//            }
//        });
//    }
//
//    //---------------------------------------------------------------------
//
//    public static void getWorks(Context context, final CallBack<ArrayList<Work>> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "activity")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Work> works = Work.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(works);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callBack.onError("exception getProjects");
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", "Error");
//                callBack.onError(err);
//            }
//        });
//    }
//
//    //---------------------------------------------------------------------
//
//    public static void getAnbar(Context context, final CallBack<ArrayList<Anbar>> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "warhouse")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Anbar> anbars = Anbar.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(anbars);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callBack.onError(e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", err);
//                callBack.onError(err);
//            }
//        });
//    }
//
//    //---------------------------------------------------------------------
//
//    public static void getProvider(Context context, final CallBack<ArrayList<TaminKonande>> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "provider")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<TaminKonande> taminKonandes = TaminKonande.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(taminKonandes);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callBack.onError(e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", err);
//                callBack.onError(err);
//            }
//        });
//    }
//    //---------------------------------------------------------------------
//
//    public static void getProduct(Context context, final CallBack<ArrayList<Product>> callBack) {
//
//        prepareServerAddress(context);
//
//        HttpHelper helper = new HttpHelper(context, getWEBSERVICE_ADDRESS(), false, 0);
//        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "product")
//        };
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    switch (response.getStatusCode()) {
//                        case SC_UNAUTHORIZED: {
//                            callBack.onError("UNAUTHORIZED");
//                            break;
//                        }
//                        case SC_OK: {
//                            JSONArray jsonArray = new JSONArray(response.getResult());
//                            ArrayList<Product> products = Product.getArrayFromJson(jsonArray);
//                            callBack.onSuccess(products);
//                            break;
//                        }
//
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callBack.onError(e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void error(String err) {
//
//                Log.d("ali", err);
//                callBack.onError(err);
//            }
//        });
//    }
}