package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pishgamanasia.self.Helper.Webservice;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.R;

public class DeliveryActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        try {
            context=this;

            String cardId = getIntent().getStringExtra("cardId");
            Webservice.sendCard(context,cardId,new CallBack<Object>() {
                @Override
                public void onSuccess(Object result) {

                    // TODO Do Something

                }

                @Override
                public void onError(String errorMessage) {
                    msgUser(errorMessage);
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delivery, menu);
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
    private void msgUser(String errMessage) {
        Toast.makeText(context, errMessage, Toast.LENGTH_SHORT).show();
    }

}
