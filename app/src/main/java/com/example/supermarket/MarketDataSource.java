package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarketDataSource {
    private SQLiteDatabase database;
    private MarketDBHelper dbHelper;

    public MarketDataSource(Context context) {
        dbHelper = new MarketDBHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertMarketRate(MarketRate m) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("marketname", m.getMarketName());
            initialValues.put("streetaddress", m.getStreetAddress());
            initialValues.put("city", m.getCity());
            initialValues.put("state", m.getState());
            initialValues.put("zipcode", m.getZipCode());
            initialValues.put("liquorrate", m.getLiquorRate());
            initialValues.put("producerate", m.getProduceRate());
            initialValues.put("meatrate", m.getMeatRate());
            initialValues.put("cheeserate", m.getCheeseRate());
            initialValues.put("checkoutrate", m.getCheckoutRate());
            initialValues.put("avgrate", m.getAvgRate());

            didSucceed = database.insert("marketrate", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateMarketRate(MarketRate m) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) m.getMarketID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("marketname", m.getMarketName());
            updateValues.put("streetaddress", m.getStreetAddress());
            updateValues.put("city", m.getCity());
            updateValues.put("state", m.getState());
            updateValues.put("zipcode", m.getZipCode());
            updateValues.put("liquorrate", m.getLiquorRate());
            updateValues.put("producerate", m.getProduceRate());
            updateValues.put("meatrate", m.getMeatRate());
            updateValues.put("cheeserate", m.getCheeseRate());
            updateValues.put("checkoutrate", m.getCheckoutRate());
            updateValues.put("avgrate", m.getAvgRate());


            didSucceed = database.update("marketrate", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastMarketId() {
        int lastId;
        try {
            String query = "Select MAX(_id) from marketrate";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<MarketRate> getMarkets() {
        ArrayList<MarketRate> markets = new ArrayList<MarketRate>();
        try {
            String query = "SELECT  * FROM marketrate";
            Cursor cursor = database.rawQuery(query, null);

            MarketRate newMarket;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newMarket = new MarketRate();
                newMarket.setMarketID(cursor.getInt(0));
                newMarket.setMarketName(cursor.getString(1));
                newMarket.setStreetAddress(cursor.getString(2));
                newMarket.setCity(cursor.getString(3));
                newMarket.setState(cursor.getString(4));
                newMarket.setZipCode(cursor.getString(5));
                newMarket.setLiquorRate(cursor.getInt(6));
                newMarket.setProduceRate(cursor.getInt(7));
                newMarket.setMeatRate(cursor.getInt(8));
                newMarket.setCheeseRate(cursor.getInt(9));
                newMarket.setCheckoutRate(cursor.getInt(10));
                newMarket.setAvgRate(cursor.getDouble(11));

                markets.add(newMarket);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            markets = new ArrayList<MarketRate>();
        }
        return markets;
    }

    public MarketRate getSpecificMarket(int marketId) {
        MarketRate market = new MarketRate();
        String query = "SELECT  * FROM marketrate WHERE _id =" + marketId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            market.setMarketID(cursor.getInt(0));
            market.setMarketName(cursor.getString(1));
            market.setStreetAddress(cursor.getString(2));
            market.setCity(cursor.getString(3));
            market.setState(cursor.getString(4));
            market.setZipCode(cursor.getString(5));
            market.setLiquorRate(cursor.getInt(6));
            market.setProduceRate(cursor.getInt(7));
            market.setMeatRate(cursor.getInt(8));
            market.setCheeseRate(cursor.getInt(9));
            market.setCheckoutRate(cursor.getInt(10));
            market.setAvgRate(cursor.getDouble(11));


            cursor.close();
        }
        return market;
    }

    public boolean deleteMarket(int marketId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("marketrate", "_id=" + marketId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }

}

