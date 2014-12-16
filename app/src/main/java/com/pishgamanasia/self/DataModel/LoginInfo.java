package com.pishgamanasia.self.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-02.
 */
public class LoginInfo implements Serializable{

    String token;
    String name;
    int resturantId;
    String resturantName;
    String deliverPersonel;
    ArrayList<Meal> meals;


    public LoginInfo(String token, String name, int resturantId, String resturantName, String deliverPersonel,ArrayList<Meal> meals) {
        this.token = token;
        this.name = name;
        this.resturantId = resturantId;
        this.resturantName = resturantName;
        this.deliverPersonel = deliverPersonel;
        this.meals=meals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public String getDeliverPersonel() {
        return deliverPersonel;
    }

    public void setDeliverPersonel(String deliverPersonel) {
        this.deliverPersonel = deliverPersonel;
    }
}
