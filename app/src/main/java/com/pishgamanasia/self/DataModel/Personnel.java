package com.pishgamanasia.self.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pishgamanasia.self.R;

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
/*
    public View getView(Context context, View oldView) {

        if (oldView == null || !(oldView.getTag() instanceof Personnel)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_personnel, null);
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

        holder.log = this;

        if (holder.request == null)
            holder.request = (TextView) view.findViewById(R.id.request);

        if (holder.response == null)
            holder.response = (TextView) view.findViewById(R.id.response);

        if (holder.date == null)
            holder.date = (TextView) view.findViewById(R.id.date);


        holder.request.setText(getRequest());
        holder.response.setText(getResponse());
        holder.date.setText(getDate());
    }


    public class Holder {
        TextView request;
        TextView response;
        TextView date;

        Log log;
    }
*/
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
