package com.example.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MarketDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "marketrate.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_CONTACT =
            "create table marketrate (_id integer primary key autoincrement, "
                    + "marketname text not null, streetaddress text, "
                    + "city text, state text, zipcode text, "
                    + "liquorrate integer,producerate integer, meatrate integer, "
                    + "cheeserate integer,checkoutrate integer);";

    public MarketDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MarketDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS marketrate");
        onCreate(db);
    }
}
