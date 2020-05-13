package com.SmartJeagers.CheckFuel.models;

import android.util.Log;

public class DayOfUse {
    private double kmPerDay;
    private double VolumePerDay;
    private String date;

    public DayOfUse(double kmPerDay, double volumePerDay, String date) {
        this.kmPerDay = kmPerDay;
        VolumePerDay = volumePerDay;
        this.date = date;
    }

    public DayOfUse() {
    }

    public double getKmPerDay() {
        return kmPerDay;
    }

    public void setKmPerDay(double kmPerDay) {
        this.kmPerDay = kmPerDay;
    }

    public double getVolumePerDay() {
        return VolumePerDay;
    }

    public void setVolumePerDay(double volumePerDay) {
        VolumePerDay = volumePerDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
