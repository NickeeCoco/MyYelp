package com.nickeecoco.myyelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

    public FavoritesDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITES (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, RATING REAL, PRICE TEXT, CATEGORIES TEXT, TELEPHONE TEXT, ADDRESS TEXT, IMAGE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
