package com.ridha.e_event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_KONTAK = "tbKontak";
    public static final String TABLE_KALENDER = "tbKalender";
    public static final String TABLE_BERITA = "tbBerita";
    public static final String KEY_ID = "ID";
    public static final String KEY_KONTAK = "KONTAK";
    public static final String KEY_KALENDER = "KALENDER";
    public static final String KEY_BERITA = "berita";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableKontak = "CREATE TABLE " + TABLE_KONTAK + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " KONTAK TEXT)";
        String createTableKalender = "CREATE TABLE " + TABLE_KALENDER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " KALENDER TEXT)";
        db.execSQL(createTableKontak);
        db.execSQL(createTableKalender);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KONTAK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KALENDER);
        onCreate(db);
    }

    public boolean addDataKontak(String kontak) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_KONTAK, kontak);

        long result = db.insert(TABLE_KONTAK, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addDataEvent(String kontak) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_KALENDER, kontak);

        long result = db.insert(TABLE_KALENDER, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListKontak(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_KONTAK, null);
        return data;
    }

    public Cursor getListKalender(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_KALENDER, null);
        return data;
    }
}
