package com.pishgamanasia.self.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-12-06.
 */
public class Personnel {
    int id;

    String name;
    String family;
    String nationalNo;
    String imageUrl;

    public Personnel(int id, String name, String family, String nationalNo, String imageUrl) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalNo = nationalNo;
        this.imageUrl = imageUrl;
    }

    public static ArrayList<Personnel> getArrayFromJson(String personnelJson) {
        ArrayList<Personnel> itemlist = null;
        try {

            JSONArray jsonArray = new JSONArray(personnelJson);
            itemlist = new ArrayList<Personnel>();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

                int id = obj.getInt("Id");
                String name = obj.getString("Name");
                String family= obj.getString("Family");
                String nationalNo= obj.getString("NationalNo");
                String imageUrl= obj.getString("ImageUrl");
                Personnel personnel = new Personnel(id, name, family, nationalNo, imageUrl);
                itemlist.add(personnel);
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

    public String getNationalNo() {
        return nationalNo;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
