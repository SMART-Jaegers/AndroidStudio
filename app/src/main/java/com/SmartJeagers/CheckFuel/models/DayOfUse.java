package com.SmartJeagers.CheckFuel.models;

public class DayOfUse {
    private double kmPerDay;
    private double VolumePerDay;
    private String day;

    public DayOfUse(double kmPerDay, double volumePerDay, String day) {
        this.kmPerDay = kmPerDay;
        VolumePerDay = volumePerDay;
        this.day = day;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
