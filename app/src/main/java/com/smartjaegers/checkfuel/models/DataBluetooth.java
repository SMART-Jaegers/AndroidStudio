package com.smartjaegers.checkfuel.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.smartjaegers.checkfuel.managers.AuthenticationManager;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForQuality;

import java.util.LinkedList;
import java.util.List;

public class DataBluetooth {

    private List<Quality> listOfTop;

    private static final double MIN_SPEED_FOR_CALCULATIONS = 72.0;

    private double speedInKmPerHour;
    private double revolutionPerMinute;
    private double fuelFlowRate;
    private double currentVolumeInPercent; // З цього перетворювати у літри, знаючи машину
    private double currentVolumeInLiters;
    private double currentFuelQuality;
    private double alreadyKm; //з OBD
    private double litersPerKm; //(літриДо - літриПісля)/кілометри з OBD

    private OnGetResult listener;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("theMessage");
            String[] data = message.split(" ");
            try {
                speedInKmPerHour = Double.parseDouble(data[0]);
                revolutionPerMinute = Double.parseDouble(data[1]);
                currentVolumeInPercent = Double.parseDouble(data[5]);
                fuelFlowRate = Double.parseDouble(data[7]);
                litersPerKm = Double.parseDouble(data[8]);
                alreadyKm = Double.parseDouble(data[9]);
                currentVolumeInLiters = currentVolumeInPercent * 55;
                listener.onSuccess();
                Log.d("---DataBluetooth: ", "broadcastReceiver: onReceive: "+ speedInKmPerHour);
            } catch (Exception e) {
                e.printStackTrace();
                listener.onFailure();
            }
        }
    };

    private double liter; //з OBD
    private double alreadyKm; //з OBD
    private double litersPerKm; //(літриДо - літриПісля)/кілометри з OBD

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double calculateQuality() {
        currentFuelQuality = 1 / calculateAverageOfInvertedQuality();
        return currentFuelQuality;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private double calculateAverageOfInvertedQuality() {
        LinkedList<Double> listToCalculate = addInverseQualityToList();
        if (listToCalculate.size() != 0) {
            return listToCalculate.stream().reduce(0.0, Double::sum) / listToCalculate.size();
        }
        return 1;
    }

    private LinkedList<Double> addInverseQualityToList() {
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

    private double calculateInverseQuality(double speedInKmPerHour, double revolutionPerMinute, double fuelFlowRate) {
        return fuelFlowRate * revolutionPerMinute / (speedInKmPerHour * speedInKmPerHour);
    }

//*********************************************

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double calculatePredictionOnKm() {
        return /*currentVolumeInLiters*/10.0 * calculateCoefficient() / 1.0/*fuelFlowRate*/;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private double calculateCoefficient() {
        return calculateQuality() / calculateAverageTopQuality();
    }

    private double calculateAverageTopQuality() {
        double allSum = 0;
        double allNumber = 0;
        AuthenticationManager authenticationManager = new AuthenticationManager();
        if (authenticationManager.entryToDatabase()) {
            DatabaseManagerForQuality.readQuality(new OnGetResult() {
                @Override
                public void onSuccess() {
                    listOfTop = DatabaseManagerForQuality.getQualities();
                    Log.i("---Quality---", "success reading quality");
                }

                @Override
                public void onStart() {
                    Log.i("---Quality---", "start reading quality");
                }

                @Override
                public void onFailure() {
                    Log.i("---Quality---", "fail reading quality");
                }
            });

            for (int count = 0; count < listOfTop.size(); count++) {
                allSum += listOfTop.get(count).getRate() * listOfTop.get(count).getNumberOfUse();
                allNumber += listOfTop.get(count).getNumberOfUse();
            }

            return allSum / allNumber;
        }
        return 1;
    }

    public void setListener(OnGetResult listener) {
        this.listener = listener;
        this.listener.onStart();
    }

    public double getCurrentVolumeInLiters() {
        return currentVolumeInLiters;
    }

    public void setCurrentVolumeInLiters(double currentVolumeInLiters) {
        this.currentVolumeInLiters = currentVolumeInLiters;
    }

    public List<Quality> getListOfTop() {
        return listOfTop;
    }

    public void setListOfTop(List<Quality> listOfTop) {
        this.listOfTop = listOfTop;
    }

    public double getSpeedInKmPerHour() {
        return speedInKmPerHour;
    }

    public void setSpeedInKmPerHour(double speedInKmPerHour) {
        this.speedInKmPerHour = speedInKmPerHour;
    }

    public double getRevolutionPerMinute() {
        return revolutionPerMinute;
    }

    public void setRevolutionPerMinute(double revolutionPerMinute) {
        this.revolutionPerMinute = revolutionPerMinute;
    }

    public double getFuelFlowRate() {
        return fuelFlowRate;
    }

    public void setFuelFlowRate(double fuelFlowRate) {
        this.fuelFlowRate = fuelFlowRate;
    }

    public double getCurrentVolumeInPercent() {
        return currentVolumeInPercent;
    }

    public void setCurrentVolumeInPercent(double currentVolumeInPercent) {
        this.currentVolumeInPercent = currentVolumeInPercent;
    }

    public double getCurrentVolumeInLiters() {
        return currentVolumeInLiters;
    }

    public void setCurrentVolumeInLiters(double currentVolumeInLiters) {
        this.currentVolumeInLiters = currentVolumeInLiters;
    }

    public double getCurrentFuelQuality() {
        return currentFuelQuality;
    }

    public void setCurrentFuelQuality(double currentFuelQuality) {
        this.currentFuelQuality = currentFuelQuality;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public double getAlreadyKm() {
        return alreadyKm;
    }

    public void setAlreadyKm(double alreadyKm) {
        this.alreadyKm = alreadyKm;
    }

    public double getLitersPerKm() {
        return litersPerKm;
    }

    public void setLitersPerKm(double litersPerKm) {
        this.litersPerKm = litersPerKm;
    }
}
