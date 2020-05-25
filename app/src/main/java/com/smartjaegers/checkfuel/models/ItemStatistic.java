package com.smartjaegers.checkfuel.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemStatistic extends Refill implements Parcelable {
    private double distance;
    private double volume;
    private String timeDriving;
    private String dateOfTesting;
    private double rate;

    public ItemStatistic(double volumeFillReal, double volumeFillExpected, double density,
                         String nameStation, String typeFuel, String date, double distance, double volume, String timeDriving, String dateOfTesting, double rate) {
        super(volumeFillReal, volumeFillExpected, density, nameStation, typeFuel, date);
        this.distance = distance;
        this.volume = volume;
        this.timeDriving = timeDriving;
        this.dateOfTesting = dateOfTesting;
        this.rate = rate;

    }

    public ItemStatistic(Refill refill, double distance, double volume, String timeDriving, String dateOfTesting, double rate) {
        super(refill.getVolumeFillReal(), refill.getVolumeFillExpected(), refill.getDensity(),
                refill.getNameStation(), refill.getTypeFuel(), refill.getDate());
        this.distance = distance;
        this.volume = volume;
        this.timeDriving = timeDriving;
        this.dateOfTesting = dateOfTesting;
        this.rate = rate;
    }

    protected ItemStatistic(Parcel in) {
        distance = in.readDouble();
        volume = in.readDouble();
        timeDriving = in.readString();
        dateOfTesting = in.readString();
        rate = in.readDouble();
    }

    public static final Creator<ItemStatistic> CREATOR = new Creator<ItemStatistic>() {
        @Override
        public ItemStatistic createFromParcel(Parcel in) {
            double volumeFillReal = in.readDouble();
            double volumeFillExpected = in.readDouble();
            double density = in.readDouble();
            String nameStation = in.readString();
            String typeFuel = in.readString();
            String date = in.readString();
            double distance = in.readDouble();
            double volume = in.readDouble();
            String timeDriving = in.readString();
            String dateOfTesting = in.readString();
            double rate = in.readDouble();

            return new ItemStatistic(volumeFillReal, volumeFillExpected, density, nameStation, typeFuel, date, distance, volume, timeDriving, dateOfTesting, rate);
        }

        @Override
        public ItemStatistic[] newArray(int size) {
            return new ItemStatistic[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(getVolumeFillReal());
        parcel.writeDouble(getVolumeFillExpected());
        parcel.writeDouble(getDensity());
        parcel.writeString(getNameStation());
        parcel.writeString(getTypeFuel());
        parcel.writeString(getDate());
        parcel.writeDouble(getDistance());
        parcel.writeDouble(getVolume());
        parcel.writeString(getTimeDriving());
        parcel.writeString(getDateOfTesting());
        parcel.writeDouble(getRate());
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getTimeDriving() {
        return timeDriving;
    }

    public void setTimeDriving(String timeDriving) {
        this.timeDriving = timeDriving;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDateOfTesting() {
        return dateOfTesting;
    }

    public void setDateOfTesting(String dateOfTesting) {
        this.dateOfTesting = dateOfTesting;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
