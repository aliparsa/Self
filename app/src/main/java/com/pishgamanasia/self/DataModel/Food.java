package com.pishgamanasia.self.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pishgamanasia.self.Interface.ListViewItemINTERFACE;
import com.pishgamanasia.self.R;

/**
 * Created by parsa on 2014-12-06.
 */
public class Food implements ListViewItemINTERFACE {
    int id;
    String caption;
    int count;

    public Food(int id, String caption, int count) {
        this.id = id;
        this.caption = caption;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        //return super.equals(o);
        Food other = ((Food)o);
        return this.id == other.getId();
    }

    public View getView(Context context, View oldView) {

        if (oldView == null || !(oldView.getTag() instanceof Food)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_food, null);
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

        holder.food = this;

        if (holder.name == null)
            holder.name = (TextView) view.findViewById(R.id.food_name);

        if (holder.count == null)
            holder.count = (TextView) view.findViewById(R.id.food_count);

        holder.name.setText(getCaption());
        holder.count.setText(getCount() + "");
    }


    public class Holder {
        TextView name;
        TextView count;

        Food food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
