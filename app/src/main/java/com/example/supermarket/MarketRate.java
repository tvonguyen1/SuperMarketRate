package com.example.supermarket;

import java.util.Calendar;

public class MarketRate  {
    private int marketID;
    private String marketName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private int liquorRate;
    private int produceRate;
    private int meatRate;
    private int cheeseRate;
    private int checkoutRate;
    private double avgRate;


    public MarketRate() {
        marketID = -1;

    }

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int marketID) {
        this.marketID = marketID;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCheeseRate() {
        return cheeseRate;
    }

    public int getCheckoutRate() {
        return checkoutRate;
    }

    public int getLiquorRate() {
        return liquorRate;
    }

    public int getMeatRate() {
        return meatRate;
    }

    public int getProduceRate() {
        return produceRate;
    }

    public void setCheeseRate(int cheeseRate) {
        this.cheeseRate = cheeseRate;
    }

    public void setCheckoutRate(int easeRate) {
        this.checkoutRate = easeRate;
    }

    public void setLiquorRate(int liquorRate) {
        this.liquorRate = liquorRate;
    }

    public void setMeatRate(int meatRate) {
        this.meatRate = meatRate;
    }

    public void setProduceRate(int produceRate) {
        this.produceRate = produceRate;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }
}

