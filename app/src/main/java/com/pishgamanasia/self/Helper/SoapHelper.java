package com.pishgamanasia.self.Helper;

import android.content.Entity;
import android.os.Bundle;
import android.provider.Settings;

import com.pishgamanasia.self.DataModel.LoginInfo;
import com.pishgamanasia.self.Interface.CallBack;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-03.
 */
public class SoapHelper {

    String NAMESPACE;
    String METHOD_NAME;
    String URL;
    String SOAP_ACTION1;


    public SoapHelper(String namespace, String methodName, String url, String soapAction) {
        this.NAMESPACE = namespace;
        this.METHOD_NAME = methodName;
        this.URL = url;
        this.SOAP_ACTION1 = soapAction;
    }

    public String SendRequestToServer(final ArrayList<String> names, final ArrayList<String> values, final CallBack<JSONObject> callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                    for (int i = 0; i < names.size(); i++) {
                        request.addProperty(names.get(i), values.get(i));
                    }

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    //this is the actual part that will call the webservice
                    androidHttpTransport.call(SOAP_ACTION1, envelope);

                    // Get the SoapResult from the envelope body.
                    SoapObject result = (SoapObject) envelope.bodyIn;

                    if (result != null) {

                        // Extract JSON Result From Response
                        JSONObject jsonReceivedData = new JSONObject(result.getProperty(0).toString());
                        callback.onSuccess(jsonReceivedData);

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

        return null;
    }
}
