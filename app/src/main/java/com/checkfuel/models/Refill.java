package com.checkfuel.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Refill {
    private double volumeFillReal;
    private double volumeFillExpected;
    private double density;
    private boolean qualityOfFuel;
    private String nameStation;
    private String typeFuel;

    public Refill(double volumeFillReal, double volumeFillExpected, double density, boolean qualityOfFuel, String nameStation, String typeFuel) {
        this.volumeFillReal = volumeFillReal;
        this.volumeFillExpected = volumeFillExpected;
        this.density = density;
        this.qualityOfFuel = qualityOfFuel;
        this.nameStation = nameStation;
        this.typeFuel = typeFuel;
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

    public boolean isQualityOfFuel() {
        return qualityOfFuel;
    }

    public void setQualityOfFuel(boolean qualityOfFuel) {
        this.qualityOfFuel = qualityOfFuel;
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
}




