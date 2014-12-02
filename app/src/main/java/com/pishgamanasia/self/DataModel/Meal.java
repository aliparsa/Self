package com.pishgamanasia.self.DataModel;

import android.support.v7.internal.view.menu.MenuWrapperFactory;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-02.
 */
public class Meal {
    private String meal;

    public Meal(String meal) {
        this.meal = meal;
    }


    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public static ArrayList<Meal> getArrayFromJson(String mealsJson) {
        ArrayList<Meal> itemlist = null;
        try {

            JSONArray jsonArray = new JSONArray(mealsJson);
            itemlist = new ArrayList<Meal>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Meal meal = new Meal(jsonArray.get(i).toString());
                itemlist.add(meal);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return itemlist;
    }
}
