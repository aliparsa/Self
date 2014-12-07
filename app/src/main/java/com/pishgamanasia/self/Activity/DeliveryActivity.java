package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.pishgamanasia.self.Adapter.ListViewObjectAdapter;
import com.pishgamanasia.self.DataModel.Food;
import com.pishgamanasia.self.DataModel.LogHelper;
import com.pishgamanasia.self.DataModel.Reserve;
import com.pishgamanasia.self.DataModel.ServerCardResponse;
import com.pishgamanasia.self.Helper.Webservice;
import com.pishgamanasia.self.Interface.CallBack;
import com.pishgamanasia.self.Interface.ListViewItemINTERFACE;
import com.pishgamanasia.self.R;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliveryActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        try {
            context=this;

            String cardId = getIntent().getStringExtra("cardId");
            Webservice.sendCard(context,cardId,new CallBack<ServerCardResponse>() {
                @Override
                public void onSuccess(ServerCardResponse result) {

                    // TODO Do Something
                    showInformation(result);

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

    private void showInformation(ServerCardResponse result) {

        ListView lvPersonnels = (ListView) findViewById(R.id.listView);
        ListView lvReserve = (ListView) findViewById(R.id.listView2);
        ListView lvTotal = (ListView) findViewById(R.id.listView3);

        ListViewObjectAdapter adapterPersonnel = new ListViewObjectAdapter(context, result.getPersonnels());
        lvPersonnels.setAdapter(adapterPersonnel);

        ListViewObjectAdapter adapterReserve = new ListViewObjectAdapter(context, result.getReserves());
        lvReserve.setAdapter(adapterReserve);


//        Bundle bundle = new Bundle();
//        for (Reserve reserve:result.getReserves()) {
//
//            ArrayList<Food> foods = reserve.getFoods();
//
//            for (Food food:foods) {
//                if (bundle.containsKey(food.getCaption())){
//                    bundle.putInt(food.getCaption(),bundle.getInt(food.getCaption())+food.getCount());
//                }else{
//                    bundle.putInt(food.getCaption(),food.getCount());
//                }
//            }
//        }

        /*
        Map<Food,Integer> hashMap= new HashMap<Food, Integer>();
        for (Reserve reserve:result.getReserves()) {
            ArrayList<Food> foods = reserve.getFoods();
            for (Food food:foods) {
                if(hashMap.containsKey(food)){
                    int oldCount = hashMap.get(food);
                    hashMap.put(food,oldCount+food.getCount());
                }else{
                hashMap.put(food,food.getCount());
                }
            }
        }
        */

        ArrayList<Food> newFoods = new ArrayList<Food>();

        for (Reserve reserve:result.getReserves()) {
            ArrayList<Food> foods = reserve.getFoods();
            for (Food food : foods) {

                boolean set = false;
                for (Food oldFood:newFoods){

                    if(oldFood == food){
                        oldFood.setCount(oldFood.getCount() + food.getCount());
                        set = true;
                    }
                }

                if(!set){
                    newFoods.add(food);
                }
            }
        }

        ListViewObjectAdapter adapterTotal = new ListViewObjectAdapter(context, newFoods);
        lvTotal.setAdapter(adapterTotal);

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
