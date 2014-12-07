package com.pishgamanasia.self.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pishgamanasia.self.Interface.ListViewItemINTERFACE;
import com.pishgamanasia.self.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-06.
 */
public class Reserve implements ListViewItemINTERFACE {
    int id;
    String name;
    String family;
    String deliveryStatus;
    String deliverDate;
    ArrayList<Food> foods;

    public Reserve(int id, String name, String family, String deliveryStatus, String deliverDate, ArrayList<Food> foods) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.deliveryStatus = deliveryStatus;
        this.deliverDate = deliverDate;
        this.foods = foods;
    }

    public static ArrayList<Reserve> getArrayFromJson(String ReserveJson) {
        ArrayList<Reserve> itemlist = null;
        try {

            JSONArray jsonArray = new JSONArray(ReserveJson);
            itemlist = new ArrayList<Reserve>();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);


                int id = obj.getInt("Id");
                String name = obj.getString("Name");
                String family = obj.getString("Family");
                String deliveryStatus = obj.getString("DeliveryStatus");
                String deliverDate = "2014/12/07 09:35" ;
                        //obj.getString("DeliveryDate");

                //foods
                ArrayList<Food> foods = new ArrayList<Food>();
                JSONArray foodsArray = obj.getJSONArray("Foods");
                for (int ii = 0; ii < foodsArray.length(); ii++) {
                    JSONObject objFood = foodsArray.getJSONObject(ii);
                    foods.add(new Food(objFood.getInt("Id"), objFood.getString("Caption"), objFood.getInt("Count")));
                }

                Reserve reserve = new Reserve(id, name, family, deliveryStatus, deliverDate, foods);
                itemlist.add(reserve);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return itemlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public View getView(Context context, View oldView) {

        if (oldView == null || !(oldView.getTag() instanceof Reserve)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_reserve, null);
            Holder holder = new Holder();
            oldView.setTag(holder);
            getItem(holder, oldView);
            return oldView;
        } else {
            Holder holder = (Holder) oldView.getTag();
            getItem(holder, oldView);
            return oldView;
        }
    }

    private void getItem(Holder holder, View view) {

        holder.reserve = this;

        if (holder.name == null)
            holder.name = (TextView) view.findViewById(R.id.name);

//        if (holder.family == null)
//            holder.family = (TextView) view.findViewById(R.id.family);

        if (holder.deliveryStatus == null)
            holder.deliveryStatus = (TextView) view.findViewById(R.id.deliveryStatus);

        if (holder.deliverDate == null)
            holder.deliverDate = (TextView) view.findViewById(R.id.deliverDate);

        if (holder.foods == null)
            holder.foods = (TextView) view.findViewById(R.id.foods);

        if (holder.statusImage == null)
            holder.statusImage = (ImageView) view.findViewById(R.id.status_image);


        holder.name.setText("نام شخص"+" : "+getName()+" "+getFamily());
        //holder.family.setText(getFamily());

        if(getDeliveryStatus().equals("0")) {
            holder.deliveryStatus.setText("وضعیت تحویل" + " : " + "تحویل نشده");
            holder.deliverDate.setText("");

            holder.statusImage.setImageResource(R.drawable.ic_steak);
        }
        else {
            holder.deliveryStatus.setText("وضعیت تحویل" + " : " + "تحویل شده");
            holder.deliverDate.setText("تاریخ تحویل"+" : "+getDeliverDate());

            holder.statusImage.setImageResource(R.drawable.ic_steak_bw);
        }


        String foodList = "";
        for (int i = 0; i < getFoods().size(); i++) {
             Food food = getFoods().get(i);
            foodList= foodList+"● "+food.getCaption()+" تعداد "+food.getCount()+ "\n";

        }
        holder.foods.setText(foodList);
    }


    public class Holder {
        TextView name;
        TextView family;
        TextView foods;
        TextView deliveryStatus;
        TextView deliverDate;
        ImageView statusImage;

        Reserve reserve;
    }
}
