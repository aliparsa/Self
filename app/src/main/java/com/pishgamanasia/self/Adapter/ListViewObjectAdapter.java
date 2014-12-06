package com.pishgamanasia.self.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.pishgamanasia.self.Interface.ListViewItemINTERFACE;

import java.util.ArrayList;

/**
 * Created by ashkan on 11/15/2014.
 */
public class ListViewObjectAdapter extends ArrayAdapter<ListViewItemINTERFACE> {




    private Context context;
    private ArrayList<ListViewItemINTERFACE> items;

    public ListViewObjectAdapter(Context context, ArrayList<ListViewItemINTERFACE> items) {
        super(context, 0);

        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ListViewItemINTERFACE item = items.get(position);

        return item.getView(context, view);
    }
}