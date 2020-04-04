package com.checkfuel.utils;

import com.checkfuel.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {

    private static final DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference("date");

    public static void writePost(double temperature, double weight, double volumeFill, double volumeRemaining, double distance) {
        String key = CHECK_FUEL_REFERENCE.push().getKey();
        Post post = new Post(temperature, weight, volumeFill, volumeRemaining, distance);
        CHECK_FUEL_REFERENCE.child(key).setValue(post);
    }
}
