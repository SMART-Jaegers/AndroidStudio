package com.SmartJeagers.CheckFuel.models;

public class ItemStatistic extends Refill {
    private double distance;

    public ItemStatistic(double volumeFillReal, double volumeFillExpected, double density,
                         String nameStation, String typeFuel, String date, double distance) {
        super(volumeFillReal, volumeFillExpected, density, nameStation, typeFuel, date);
        this.distance = distance;
    }

    public ItemStatistic(Refill refill, double distance) {
        super(refill.getVolumeFillReal(), refill.getVolumeFillExpected(), refill.getDensity(),
                refill.getNameStation(), refill.getTypeFuel(), refill.getDate());
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
