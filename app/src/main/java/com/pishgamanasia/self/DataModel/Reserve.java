package com.pishgamanasia.self.DataModel;

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
