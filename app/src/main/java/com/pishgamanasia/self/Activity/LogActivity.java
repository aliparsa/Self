package com.pishgamanasia.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.pishgamanasia.self.Adapter.ListViewObjectAdapter;
import com.pishgamanasia.self.Helper.LogHelper;
import com.pishgamanasia.self.Interface.ListViewItemINTERFACE;
import com.pishgamanasia.self.R;

import java.util.ArrayList;

public class LogActivity extends Activity {

    private ListView lv;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        context = this;

        lv = (ListView) findViewById(R.id.lv_log);

        loadLogs();
    }

    private void loadLogs() {

        ArrayList<ListViewItemINTERFACE> itemList = new ArrayList<ListViewItemINTERFACE>();

        LogHelper db = new LogHelper(context);

        for (LogHelper.Log log : db.getAllLog(20)) {
            itemList.add(log);
        }


        ListViewObjectAdapter adapter = new ListViewObjectAdapter(context, itemList);
        lv.setAdapter(adapter);
    }

}
