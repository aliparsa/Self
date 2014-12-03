package com.pishgamanasia.self.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pishgamanasia.self.DataModel.LogHelper;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aliparsa on 9/20/2014.
 */
public class

   DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db.db";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {



     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    /*
    public void insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(PRODUCT_KEY_ID, product.getId());
        values.put(PRODUCT_KEY_NAME, product.getName());
        this.getWritableDatabase().insert(TABLE_PRODUCT, null, values);
    }*/


    /*
    public ArrayList<Personnel> getAllPersonnels() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PERSONNEL, null);
        ArrayList<Personnel> personnels = new ArrayList<Personnel>();


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    Personnel personnel = new Personnel();
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(PERSONNEL_KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_PHONE))));

                    personnels.add(personnel);

                } while (cursor.moveToNext());

            }
        }
        return personnels;
    }
    */


    /*
    public void emptyPersonnelTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_PERSONNEL);
    }
    */

}