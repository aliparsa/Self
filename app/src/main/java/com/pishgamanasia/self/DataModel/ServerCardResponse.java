package com.pishgamanasia.self.DataModel;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-06.
 */
public class ServerCardResponse {
    ArrayList<Personnel> personnels;
    ArrayList<Reserve> reserves;
    String message;
    String status;


    public ServerCardResponse(ArrayList<Personnel> personnels, ArrayList<Reserve> reserves, String message, String status) {
        this.personnels = personnels;
        this.reserves = reserves;
        this.message = message;
        this.status = status;
    }


    public ArrayList<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(ArrayList<Personnel> personnels) {
        this.personnels = personnels;
    }

    public ArrayList<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
