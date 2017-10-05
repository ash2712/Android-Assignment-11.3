package com.example.ayush.imagedatabaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayush on 10/3/2017.
 */

public class myDatabase extends SQLiteOpenHelper{
    public final static String DATABASE_NAME = "My database";
    public static final String TABLE_NAME = "MyTable";
    public final static int VERSION = 1;
    public static final String KEY_IMAGE = "image_data";


    public myDatabase(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"  + KEY_IMAGE+ " BLOB" + ")";
        //table is created
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEntry(byte[] image) throws SQLiteException{
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new  ContentValues();
        values.put(KEY_IMAGE,image);
        //adds an entry into the database
        database.insert(TABLE_NAME, null, values );
    }
}
