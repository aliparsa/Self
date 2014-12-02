package com.pishgamanasia.self.DataModel;

import java.io.Serializable;

/**
 * Created by parsa on 2014-12-02.
 */
public class LoginInfo implements Serializable{

    String token;
    String name;
    int resturantId;
    String resturantName;
    String deliverPersonel;

    public LoginInfo(String token, String name, int resturantId, String resturantName, String deliverPersonel) {
        this.token = token;
        this.name = name;
        this.resturantId = resturantId;
        this.resturantName = resturantName;
        this.deliverPersonel = deliverPersonel;
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
