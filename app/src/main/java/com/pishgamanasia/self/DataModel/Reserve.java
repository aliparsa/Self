package com.pishgamanasia.self.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-06.
 */
public class Reserve {
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
                String deliverDate = obj.getString("DeliveryDate");

                //foods
                ArrayList<Food> foods = new ArrayList<Food>();
                JSONArray foodsArray = obj.getJSONArray("Foods");
                for (int ii = 0; ii < foodsArray.length(); ii++) {
                    JSONObject objFood = foodsArray.getJSONObject(i);
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
}
