package com.SmartJeagers.CheckFuel.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Refill {
    private double volumeFillReal;
    private double volumeFillExpected;
    private double density;
    private String nameStation;
    private String typeFuel;
    private String date;

    public Refill(double volumeFillReal, double volumeFillExpected, double density,
                  String nameStation, String typeFuel, String date) {
        this.volumeFillReal = volumeFillReal;
        this.volumeFillExpected = volumeFillExpected;
        this.density = density;
        this.nameStation = nameStation;
        this.typeFuel = typeFuel;
        this.date = date;
    }

    public Refill() {
    }

    public double getVolumeFillReal() {
        return volumeFillReal;
    }

    public void setVolumeFillReal(double volumeFillReal) {
        this.volumeFillReal = volumeFillReal;
    }

    public double getVolumeFillExpected() {
        return volumeFillExpected;
    }

    public void setVolumeFillExpected(double volumeFillExpected) {
        this.volumeFillExpected = volumeFillExpected;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(String typeFuel) {
        this.typeFuel = typeFuel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}




