package com.smartjaegers.checkfuel.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Quality {
    private double rate;
    private int numberOfUse;

    public Quality() {

    }

    public Quality(double quality, int numberOfUse) {
        this.rate = quality;
        this.numberOfUse = numberOfUse;
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
}




