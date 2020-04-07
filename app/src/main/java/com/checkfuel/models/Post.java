package com.checkfuel.models;

import com.google.firebase.database.IgnoreExtraProperties;

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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolumeFill() {
        return volumeFill;
    }

    public void setVolumeFill(double volumeFill) {
        this.volumeFill = volumeFill;
    }

    public double getVolumeRemaining() {
        return volumeRemaining;
    }

    public void setVolumeRemaining(double volumeRemaining) {
        this.volumeRemaining = volumeRemaining;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
