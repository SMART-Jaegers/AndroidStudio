/*package com.smartjaegers.checkfuel.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.LinkedList;

public class DataBluetooth {

    private static final double MIN_SPEED_FOR_CALCULATIONS = 72.0;

    private double speedInKmPerHour;
    private double revolutionPerMinute;
    private double fuelFlowRate;
    private double currentVolumeInPercent; // З цього перетворювати у літри, знаючи машину
    private double currentVolumeInLiters;
    private double currentFuelQuality;

    private double predictionOnKm;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double calculateQuality() {
        currentFuelQuality = 1 / calculateAverageOfInvertedQuality();
        return currentFuelQuality;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double calculateAverageOfInvertedQuality() {
        LinkedList<Double> listToCalculate = addInverseQualityToList();
        return listToCalculate.stream().reduce(0.0, Double::sum)/listToCalculate.size();
    }

    public LinkedList<Double> addInverseQualityToList() {
        LinkedList<Double> listOfInverseQuality = new LinkedList<>();
        do {
            // брати дані з блютузу :)
            if (speedInKmPerHour >= MIN_SPEED_FOR_CALCULATIONS) {
                double inverseQuality = calculateInverseQuality(speedInKmPerHour, revolutionPerMinute, fuelFlowRate);
                listOfInverseQuality.add(inverseQuality);
            }
        } while (false);                  // доки машина їдe
        return listOfInverseQuality;
    }

    public double calculateInverseQuality(double speedInKmPerHour, double revolutionPerMinute, double fuelFlowRate) {
        return fuelFlowRate * revolutionPerMinute / (speedInKmPerHour * speedInKmPerHour);
    }

//*********************************************
    public double calculatePredictionOnKm(double currentVolumeInLiters, double fuelFlowRate, double k){
        return currentVolumeInLiters*k/fuelFlowRate;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double calculateCoefficient(){
        return calculateQuality()/calculateAverageTopQuality();
    }

    public double calculateAverageTopQuality(){
        return averageTopQuality();
    }

    public double createListOfQuality(){
        LinkedList<Double> listOfQuality = new LinkedList<>();
        listOfQuality.add()
    }

}
*/