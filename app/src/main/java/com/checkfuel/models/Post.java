package com.checkfuel.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {
    private double temperature;
    private double weight;
    private double volumeFill;
    private double volumeRemaining;
    private double distance;

    public Post() {
    }

    public Post(double temperature, double weight, double volumeFill, double volumeRemaining, double distance) {
        this.temperature = temperature;
        this.weight = weight;
        this.volumeFill = volumeFill;
        this.volumeRemaining = volumeRemaining;
        this.distance = distance;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolumeFill() {
        return volumeFill;
    }

    public double getVolumeRemaining() {
        return volumeRemaining;
    }

    public double getDistance() {
        return distance;
    }
}
