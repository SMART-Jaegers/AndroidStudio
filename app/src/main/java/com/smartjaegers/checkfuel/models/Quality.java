package com.smartjaegers.checkfuel.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Quality {
    private double rate;
    private int numberOfUse;
    private String dateOfTesting;

    public Quality() {

    }

    public Quality(double quality, int numberOfUse, String dateOfTesting) {
        this.rate = quality;
        this.numberOfUse = numberOfUse;
        this.dateOfTesting = dateOfTesting;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNumberOfUse() {
        return numberOfUse;
    }

    public void setNumberOfUse(int numberOfUse) {
        this.numberOfUse = numberOfUse;
    }

    public String getDateOfTesting() {
        return dateOfTesting;
    }

    public void setDateOfTesting(String dateOfTesting) {
        this.dateOfTesting = dateOfTesting;
    }
}




